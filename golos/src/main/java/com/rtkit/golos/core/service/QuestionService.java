package com.rtkit.golos.core.service;


import com.rtkit.golos.core.access.PollQuestionRepository;
import com.rtkit.golos.core.access.QuestionRepository;
import com.rtkit.golos.core.entity.PollQuestion;
import com.rtkit.golos.core.entity.Question;
import com.rtkit.golos.core.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final PollQuestionRepository pollQuestionRepository;

    public List<PollQuestion> getAllQuestionsByPollId(int pollId) {
        log.info("Запрос получения вопросов по id опроса: {}", pollId);

        return pollQuestionRepository.findAllByPollId(pollId);
    }

    public Question getQuestionById(int id) {
        log.info("Запрос получения вопроса по id: {}", id);

        return questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Вопрос с id:%d не найден".formatted(id)));
    }


    public Question addQuestion(Question question) {
        log.info("Запрос сохранения нового вопроса: {}", question);

        return questionRepository.save(question);
    }

    public void deleteQuestion(Integer id) {
        log.info("Запрос удаления вопроса с id = {}", id);

        questionRepository.deleteById(id);
    }
}
