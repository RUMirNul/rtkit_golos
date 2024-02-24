package org.example;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PollInviteDto {
    private String pollName;
    private String email;
    private String url;
}
