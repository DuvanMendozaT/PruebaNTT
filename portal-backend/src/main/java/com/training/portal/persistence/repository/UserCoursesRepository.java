package com.training.portal.persistence.repository;

import com.training.portal.model.rest.UserCourseResponse;
import com.training.portal.persistence.entity.UserCoursesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCoursesRepository extends JpaRepository<UserCoursesEntity, Long> {

    Optional<UserCoursesEntity> findByUser_IdAndCourse_Id(Long userId, Long courseId);

    @Query(value = """
       SELECT
         c.id          AS id,
         c.module      AS module,
         c.title       AS title,
         c.description AS description,
         c.duration    AS duration,
         uc.status     AS status
       FROM user_courses uc
       INNER JOIN courses c ON c.id = uc.course_id
       WHERE uc.user_id = :userId
    """, nativeQuery = true)
    List<UserCourseResponse> findCoursesInfoByUserId(@Param("userId") Long userId);

}
