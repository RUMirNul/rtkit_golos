package com.rtkit.golos.core.access;

import com.rtkit.golos.core.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
