package com.training.portal.service.usercourse;

import com.training.portal.model.UserCourseModel;
import com.training.portal.model.rest.UserCourseRequest;

public interface UserCourseService {

    UserCourseModel create(UserCourseRequest userCourseRequest);

    UserCourseModel update(UserCourseRequest userCourseRequest);
}
