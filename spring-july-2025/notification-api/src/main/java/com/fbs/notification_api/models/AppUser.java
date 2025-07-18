package com.fbs.notification_api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    UUID id;
    String name;
    String email;
    String password;
    Long number;
    boolean isVerified;
    String userType;
    String status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
