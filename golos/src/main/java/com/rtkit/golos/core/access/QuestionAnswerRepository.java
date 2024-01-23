package com.rtkit.golos.core.access;

import com.rtkit.golos.core.entity.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Integer> {
    List<QuestionAnswer> findAllByIdPollQuestionId(Integer pollQuestionId);
    QuestionAnswer findByIdAnswerId(int answerId);
}
