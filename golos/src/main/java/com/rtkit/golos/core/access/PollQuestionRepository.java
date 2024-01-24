package com.rtkit.golos.core.access;

import com.rtkit.golos.core.entity.PollQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PollQuestionRepository extends JpaRepository<PollQuestion, Integer> {
    List<PollQuestion> findAllByPollId(int pollId);
}
