package com.training.portal.persistence.mapper;

import com.training.portal.model.UserCourseModel;
import com.training.portal.persistence.entity.UserCoursesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface UserCourseMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "course.id", target = "courseId")
    UserCourseModel toModel(UserCoursesEntity userCoursesEntity);

}

