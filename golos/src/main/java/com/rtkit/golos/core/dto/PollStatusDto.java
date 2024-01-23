package com.rtkit.golos.core.dto;

import java.util.List;

public record PollStatusDto(List<String> statuses, int size) {
}
