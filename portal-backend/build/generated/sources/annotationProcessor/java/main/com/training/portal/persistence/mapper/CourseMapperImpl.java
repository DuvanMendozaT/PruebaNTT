package com.training.portal.persistence.mapper;

import com.training.portal.dto.CourseModel;
import com.training.portal.persistence.entity.CourseEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-22T23:05:35-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public CourseModel toModel(CourseEntity courseEntity) {
        if ( courseEntity == null ) {
            return null;
        }

        CourseModel.CourseModelBuilder courseModel = CourseModel.builder();

        courseModel.id( courseEntity.getId() );
        courseModel.module( courseEntity.getModule() );
        courseModel.title( courseEntity.getTitle() );
        courseModel.description( courseEntity.getDescription() );
        courseModel.duration( courseEntity.getDuration() );

        return courseModel.build();
    }

    @Override
    public CourseEntity toEntity(CourseModel courseModel) {
        if ( courseModel == null ) {
            return null;
        }

        CourseEntity.CourseEntityBuilder courseEntity = CourseEntity.builder();

        courseEntity.id( courseModel.getId() );
        courseEntity.title( courseModel.getTitle() );
        courseEntity.module( courseModel.getModule() );
        courseEntity.description( courseModel.getDescription() );
        courseEntity.duration( courseModel.getDuration() );

        return courseEntity.build();
    }

    @Override
    public List<CourseModel> toModels(List<CourseEntity> courseEntities) {
        if ( courseEntities == null ) {
            return null;
        }

        List<CourseModel> list = new ArrayList<CourseModel>( courseEntities.size() );
        for ( CourseEntity courseEntity : courseEntities ) {
            list.add( toModel( courseEntity ) );
        }

        return list;
    }
}
