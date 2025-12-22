package com.training.portal.dto;

import jakarta.persistence.*;
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
