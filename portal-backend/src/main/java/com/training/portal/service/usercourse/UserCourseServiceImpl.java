package com.training.portal.service.usercourse;

import com.training.portal.model.UserCourseModel;
import com.training.portal.model.rest.UserCourseRequest;
import com.training.portal.persistence.entity.CourseEntity;
import com.training.portal.persistence.entity.UserCoursesEntity;
import com.training.portal.persistence.entity.UserEntity;
import com.training.portal.persistence.mapper.UserCourseMapper;
import com.training.portal.persistence.repository.CourseRepository;
import com.training.portal.persistence.repository.UserCoursesRepository;
import com.training.portal.persistence.repository.UserRepository;
import com.training.portal.util.Constants;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class UserCourseServiceImpl implements UserCourseService{

    @Autowired
    private UserCoursesRepository userCoursesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserCourseMapper userCourseMapper;

    @Override
    @Transactional
    public UserCourseModel create(UserCourseRequest userCourseRequest) {

        log.info("inicio servicio asociacion de curso a usuario");

        Optional<UserCoursesEntity> entity = findByUserIdAndCourseId(userCourseRequest.getUserId(), userCourseRequest.getCourseId());

        if(entity.isPresent()){
            throw new IllegalStateException("Curso ya asociado al usuario");
        }

        UserEntity userEntity = userRepository.findById(userCourseRequest.getUserId()).
                orElseThrow(() -> new RuntimeException("user not found"));

        CourseEntity courseEntity= courseRepository.findById(userCourseRequest.getCourseId()).
                orElseThrow(() -> new RuntimeException("course not found"));


        UserCoursesEntity userCoursesEntity = new UserCoursesEntity();
        userCoursesEntity.setUser(userEntity);
        userCoursesEntity.setCourse(courseEntity);
        userCoursesEntity.setStatus(Constants.STATUS_REGISTERED);


        return userCourseMapper.toModel(userCoursesRepository.save(userCoursesEntity));
    }

    @Override
    @Transactional
    public UserCourseModel update(UserCourseRequest userCourseRequest) {
        log.info("inicio servicio actuallizacion de estado de curso por usuario");
        UserCoursesEntity userCoursesEntity = findByUserIdAndCourseId(userCourseRequest.getUserId(), userCourseRequest.getCourseId())
                .orElseThrow(()-> new RuntimeException("course or user not found"));

        userCoursesEntity.setStatus(courseStatus(userCourseRequest.getStatus()));
        return userCourseMapper.toModel(userCoursesRepository.save(userCoursesEntity));
    }

    public Optional<UserCoursesEntity> findByUserIdAndCourseId(Long userId, Long courseId){
        return userCoursesRepository.findByUser_IdAndCourse_Id(userId, courseId);
    }

    public String courseStatus(int statusId){
        return switch (statusId) {
            case 0 -> Constants.STATUS_REGISTERED;
            case 1 -> Constants.STATUS_COMPLETED;
            default -> throw new IllegalStateException("invalid Status: " + statusId);
        };
    }
}
