package com.rtkit.golos.core.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseErrorWebResponse {
    private String errorMessage;
}
