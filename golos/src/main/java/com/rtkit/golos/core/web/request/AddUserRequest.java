package com.rtkit.golos.core.web.request;

import com.rtkit.golos.core.entity.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@Schema(description = "Запрос для создания пользователя.")
public class AddUserRequest {
    @NotNull(message = "Поле firstName не должно быть null.")
    @Length(max = 30, message = "Максимальная длина 30 символов.")
    @Schema(description = "Имя пользователя.")
    private String firstName;

    @NotNull(message = "Поле lastName не должно быть null.")
    @Length(max = 30, message = "Максимальная длина 30 символов.")
    @Schema(description = "Фамилия пользователя.")
    private String lastName;

    @NotNull(message = "Поле email не должно быть null.")
    @Length(max = 320, message = "Максимальная длина 320 символов.")
    @Schema(description = "Электронная почта пользователя.")
    private String email;

    @NotNull(message = "Поле passHash не должно быть null.")
    @Length(max = 320, message = "Максимальная длина 320 символов.")
    @Schema(description = "Пароль пользователя.")
    private String passHash;

    @NotNull(message = "Поле role не должно быть null.")
    @Length(max = 255, message = "Максимальная длина 255 символов.")
    @Schema(description = "Роль пользователя.")
    private UserRole role;
}
