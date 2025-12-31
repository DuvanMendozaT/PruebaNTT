package com.training.portal.model;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {
    private String email;
    private String passwordHash;
    private String fullName;
    private String role;

}
