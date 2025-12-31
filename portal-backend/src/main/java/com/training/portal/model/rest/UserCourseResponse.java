package com.training.portal.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCourseResponse {

    private Long id;
    private String module;
    private String title;
    private String description;
    private int duration;
    private String status;
}
