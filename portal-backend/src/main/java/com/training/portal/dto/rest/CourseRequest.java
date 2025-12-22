package com.training.portal.dto.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRequest {
    @Nullable
    private Long id;
    private String title;
    private String module;
    private String description;
    private int duration;
}
