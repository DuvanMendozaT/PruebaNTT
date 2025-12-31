package com.training.portal.persistence.mapper;

import com.training.portal.model.UserModel;
import com.training.portal.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {

    UserModel toModel(UserEntity userEntity);
    UserEntity toEntity(UserModel userModel);

    List<UserModel> toModels(List<UserEntity> all);
}
