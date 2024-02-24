package com.rtkit.golos.core.access;

import com.rtkit.golos.core.entity.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Integer> {
    UserAnswer findByIdResultIdAndIdPollQuestionId(Integer resultId, Integer PollQuestionId);

    @Query(value = "SELECT COUNT(u) FROM UserAnswer u WHERE u.answerid=:answerid", nativeQuery = true)
    int countByAnswerId(@Param("answerid") Integer answerId);
}
