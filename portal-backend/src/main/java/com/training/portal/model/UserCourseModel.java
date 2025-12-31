package com.training.portal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserCourseModel {

    private Long id;
    private Long userId;
    private Long courseId;
    private String status;

}
