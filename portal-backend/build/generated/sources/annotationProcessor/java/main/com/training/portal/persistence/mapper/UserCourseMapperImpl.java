package com.training.portal.persistence.mapper;

import com.training.portal.model.UserCourseModel;
import com.training.portal.persistence.entity.CourseEntity;
import com.training.portal.persistence.entity.UserCoursesEntity;
import com.training.portal.persistence.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-29T16:33:54-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class UserCourseMapperImpl implements UserCourseMapper {

    @Override
    public UserCourseModel toModel(UserCoursesEntity userCoursesEntity) {
        if ( userCoursesEntity == null ) {
            return null;
        }

        UserCourseModel.UserCourseModelBuilder userCourseModel = UserCourseModel.builder();

        userCourseModel.userId( userCoursesEntityUserId( userCoursesEntity ) );
        userCourseModel.courseId( userCoursesEntityCourseId( userCoursesEntity ) );
        userCourseModel.id( userCoursesEntity.getId() );
        userCourseModel.status( userCoursesEntity.getStatus() );

        return userCourseModel.build();
    }

    private Long userCoursesEntityUserId(UserCoursesEntity userCoursesEntity) {
        if ( userCoursesEntity == null ) {
            return null;
        }
        UserEntity user = userCoursesEntity.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long userCoursesEntityCourseId(UserCoursesEntity userCoursesEntity) {
        if ( userCoursesEntity == null ) {
            return null;
        }
        CourseEntity course = userCoursesEntity.getCourse();
        if ( course == null ) {
            return null;
        }
        Long id = course.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
