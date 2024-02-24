package org.example;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {
    private String email;
    private String activationCode;
}