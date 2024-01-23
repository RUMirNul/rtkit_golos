package com.rtkit.golos.core.service;


import com.rtkit.golos.core.access.QuestionRepository;
import com.rtkit.golos.core.entity.Question;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    public void deleteQuestion(Integer id) {
        questionRepository.deleteById(id);
    }
}
