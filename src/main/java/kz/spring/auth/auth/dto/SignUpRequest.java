package kz.spring.auth.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Запрос на регистрацию", example = """
        {
        "username": "Jon",
        "password": "jon_password"
        }
        """)
public class SignUpRequest {

    @Schema(description = "Имя пользователя", example = "Jon")
    @NotEmpty
    @NotNull
    private String username;

    @Schema(description = "Пароль", example = "jon_2024")
    @NotEmpty
    @NotNull
    private String password;
}
