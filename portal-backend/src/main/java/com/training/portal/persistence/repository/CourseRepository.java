package com.training.portal.persistence.repository;

import com.training.portal.persistence.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

    List<CourseEntity> findAllByModule(String module);
}
