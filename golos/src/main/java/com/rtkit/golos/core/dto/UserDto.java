package com.rtkit.golos.core.dto;

import com.rtkit.golos.core.entity.GolosUser;
import com.rtkit.golos.core.entity.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@Schema(description = "Пользователь.")
public class UserDto {
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

    @NotNull(message = "Поле role не должно быть null.")
    @Length(max = 255, message = "Максимальная длина 255 символов.")
    @Schema(description = "Роль пользователя.")
    private UserRole role;

    public UserDto(GolosUser user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
