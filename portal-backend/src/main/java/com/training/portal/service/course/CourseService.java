package com.training.portal.service.course;

import com.training.portal.dto.CourseModel;
import com.training.portal.dto.rest.CourseRequest;
import com.training.portal.dto.rest.UserCourseResponse;

import java.util.List;

public interface CourseService {
    CourseModel create(CourseRequest courseRequest);

    CourseModel findById(Long courseId);

    CourseModel update(CourseRequest courseRequest);

    List<CourseModel> findAll();

    List<CourseModel> findByModule(String module);

    List<UserCourseResponse> findByUserId(Long id);
}
