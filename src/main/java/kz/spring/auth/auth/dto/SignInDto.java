package kz.spring.auth.auth.dto;

public record SignInDto(
    String login,
    String password
) {
}