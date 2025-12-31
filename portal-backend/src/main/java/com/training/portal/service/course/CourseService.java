package com.training.portal.service.course;

import com.training.portal.model.CourseModel;
import com.training.portal.model.rest.CourseRequest;
import com.training.portal.model.rest.UserCourseResponse;

import java.util.List;

public interface CourseService {
    CourseModel create(CourseRequest courseRequest);

    CourseModel findById(Long courseId);

    CourseModel update(CourseRequest courseRequest);

    List<CourseModel> findAll();

    List<CourseModel> findByModule(String module);

    List<UserCourseResponse> findByUserId(Long id);

    CourseModel deleteById(Long id);
}
