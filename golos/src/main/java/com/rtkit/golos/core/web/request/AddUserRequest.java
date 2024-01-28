package com.rtkit.golos.core.web.request;

import com.rtkit.golos.core.entity.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String passHash;
    private UserRole role;
}
