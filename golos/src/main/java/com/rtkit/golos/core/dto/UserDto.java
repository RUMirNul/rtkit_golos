package com.rtkit.golos.core.dto;

import com.rtkit.golos.core.entity.GolosUser;
import com.rtkit.golos.core.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;

    public UserDto(GolosUser user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
