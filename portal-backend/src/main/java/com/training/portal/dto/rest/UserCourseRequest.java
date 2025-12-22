package com.training.portal.dto.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCourseRequest {

    private Long userId;
    private Long courseId;
    private int status;
}
