package com.training.portal.persistence.mapper;

import com.training.portal.dto.UserModel;
import com.training.portal.persistence.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-22T23:05:34-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserModel toModel(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserModel.UserModelBuilder userModel = UserModel.builder();

        userModel.email( userEntity.getEmail() );
        userModel.passwordHash( userEntity.getPasswordHash() );
        userModel.fullName( userEntity.getFullName() );
        userModel.role( userEntity.getRole() );

        return userModel.build();
    }

    @Override
    public UserEntity toEntity(UserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.email( userModel.getEmail() );
        userEntity.passwordHash( userModel.getPasswordHash() );
        userEntity.fullName( userModel.getFullName() );
        userEntity.role( userModel.getRole() );

        return userEntity.build();
    }
}
