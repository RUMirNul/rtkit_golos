package com.rtkit.golos.core.dto;


import com.rtkit.golos.core.entity.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    private Integer id;
    private Integer questionOrder;
    private String text;
    private QuestionType type;
}
