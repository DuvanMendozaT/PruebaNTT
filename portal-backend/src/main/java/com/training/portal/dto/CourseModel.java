package com.training.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CourseModel {

    private Long id;

    private String module;

    private String title;

    private String description;

    private int duration;

}
