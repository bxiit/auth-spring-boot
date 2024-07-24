package kz.spring.auth.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Запрос на аутентификацию", example = """
        {
          "username": "Jon",
          "password": "jon_password"
        }
        """)
public class SignInRequest {

    @Schema(description = "Имя пользователя", example = "Jon")
    @NotEmpty
    @NotNull

    private String username;

    @Schema(description = "Пароль", example = "my_1secret1_password")
    @NotEmpty
    @NotNull
    private String password;
}