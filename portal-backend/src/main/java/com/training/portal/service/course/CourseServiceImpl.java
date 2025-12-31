package com.training.portal.service.course;

import com.training.portal.model.CourseModel;
import com.training.portal.model.rest.CourseRequest;
import com.training.portal.model.rest.UserCourseResponse;
import com.training.portal.persistence.entity.CourseEntity;
import com.training.portal.persistence.mapper.CourseMapper;
import com.training.portal.persistence.repository.CourseRepository;
import com.training.portal.persistence.repository.UserCoursesRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserCoursesRepository userCoursesRepository;

    @Autowired
    private CourseMapper courseMapper;


    @Override
    @Transactional
    public CourseModel create(CourseRequest courseRequest) {
        log.info("inicio servicio creacion de curso");

        CourseModel courseModel = CourseModel.builder()
                .title(courseRequest.getTitle())
                .description(courseRequest.getDescription())
                .duration(courseRequest.getDuration())
                .module(courseRequest.getModule())
                .build();

        return courseMapper.toModel(courseRepository.save(courseMapper.toEntity(courseModel)));
    }

    @Override
    public CourseModel findById(Long courseId) {
        log.info("inicio servicio consulta de curso por ID");

        return courseMapper.toModel(courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found")));
    }

    @Override
    @Transactional
    public CourseModel update(CourseRequest courseRequest) {

        log.info("inicio servicio actualizacion de curso");

        CourseModel courseModel = findById(courseRequest.getId());
        courseModel.setTitle(courseRequest.getTitle());
        courseModel.setDescription(courseRequest.getDescription());
        courseModel.setModule(courseRequest.getModule());
        courseModel.setDuration(courseRequest.getDuration());

        return courseMapper.toModel(courseRepository.save(courseMapper.toEntity(courseModel)));
    }

    @Override
    public List<CourseModel> findAll() {
        log.info("inicio servicio consulta de todos los cursos");
        return courseMapper.toModels(courseRepository.findAll());
    }

    @Override
    public List<CourseModel> findByModule(String module) {
        log.info("inicio servicio consulta de cursos por modulo");
        return courseMapper.toModels(courseRepository.findAllByModule(module));
    }

    @Override
    public List<UserCourseResponse> findByUserId(Long id) {
        log.info("inicio servicio consulta de cursos por estudiante");
        return userCoursesRepository.findCoursesInfoByUserId(id);
    }

    @Override
    public CourseModel deleteById(Long id) {
        CourseEntity existing = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        courseRepository.deleteById(id);

        return courseMapper.toModel(existing);
    }
}
