package com.training.portal.persistence.mapper;

import com.training.portal.dto.CourseModel;
import com.training.portal.persistence.entity.CourseEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface CourseMapper {

    CourseModel toModel(CourseEntity courseEntity);
    CourseEntity toEntity(CourseModel courseModel);

    List<CourseModel> toModels(List<CourseEntity> courseEntities);

}
