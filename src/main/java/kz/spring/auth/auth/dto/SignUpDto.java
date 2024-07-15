package kz.spring.auth.auth.dto;

import kz.spring.auth.auth.enums.UserRole;

public record SignUpDto(
    String login,
    String password,
    UserRole role
) {
}