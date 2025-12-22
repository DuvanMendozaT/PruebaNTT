package com.training.portal.service.course;

import com.training.portal.dto.CourseModel;
import com.training.portal.dto.rest.CourseRequest;
import com.training.portal.dto.rest.UserCourseResponse;
import com.training.portal.persistence.mapper.CourseMapper;
import com.training.portal.persistence.repository.CourseRepository;
import com.training.portal.persistence.repository.UserCoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserCoursesRepository userCoursesRepository;

    @Autowired
    private CourseMapper courseMapper;


    @Override
    public CourseModel create(CourseRequest courseRequest) {

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
        return courseMapper.toModel(courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found")));
    }

    @Override
    public CourseModel update(CourseRequest courseRequest) {
        CourseModel courseModel = findById(courseRequest.getId());


        courseModel.setTitle(courseRequest.getTitle());
        courseModel.setDescription(courseRequest.getDescription());
        courseModel.setModule(courseRequest.getModule());
        courseModel.setDuration(courseRequest.getDuration());

        return courseMapper.toModel(courseRepository.save(courseMapper.toEntity(courseModel)));
    }

    @Override
    public List<CourseModel> findAll() {
        return courseMapper.toModels(courseRepository.findAll());
    }

    @Override
    public List<CourseModel> findByModule(String module) {
        return courseMapper.toModels(courseRepository.findAllByModule(module));
    }

    @Override
    public List<UserCourseResponse> findByUserId(Long id) {
        return userCoursesRepository.findCoursesInfoByUserId(id);
    }
}
