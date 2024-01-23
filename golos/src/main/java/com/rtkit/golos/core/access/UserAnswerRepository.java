package com.rtkit.golos.core.access;

import com.rtkit.golos.core.entity.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Integer> {
}
