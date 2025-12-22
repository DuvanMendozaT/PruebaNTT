package com.training.portal.service.usercourse;

import com.training.portal.dto.UserCourseModel;
import com.training.portal.dto.rest.UserCourseRequest;

public interface UserCourseService {

    UserCourseModel create(UserCourseRequest userCourseRequest);

    UserCourseModel update(UserCourseRequest userCourseRequest);
}
