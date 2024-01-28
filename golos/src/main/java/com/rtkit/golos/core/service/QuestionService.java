package com.rtkit.golos.core.service;


import com.rtkit.golos.core.access.PollQuestionRepository;
import com.rtkit.golos.core.access.QuestionRepository;
import com.rtkit.golos.core.dto.PollQuestionDto;
import com.rtkit.golos.core.dto.QuestionDto;
import com.rtkit.golos.core.entity.PollQuestion;
import com.rtkit.golos.core.entity.Question;
import com.rtkit.golos.core.exception.NotFoundException;
import com.rtkit.golos.core.mapper.QuestionMapper;
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
    private final QuestionMapper questionMapper;

    public List<PollQuestionDto> getAllQuestionsByPollId(int pollId) {
        log.info("Запрос получения вопросов по id опроса: {}", pollId);

        List<PollQuestion> pollQuestions = pollQuestionRepository.findAllByPollIdId(pollId);
        return questionMapper.toPollQuestionDtos(pollQuestions);
    }

    public QuestionDto getQuestionById(int id) {
        log.info("Запрос получения вопроса по id: {}", id);

        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Вопрос с id:%d не найден".formatted(id)));

        return questionMapper.toQuestionDto(question);
    }


    public QuestionDto addQuestion(QuestionDto questionDto) {
        log.info("Запрос сохранения нового вопроса: {}", questionDto);

        Question question = questionMapper.toQuestion(questionDto);
        Question savedQuestion = questionRepository.save(question);
        return questionMapper.toQuestionDto(savedQuestion);
    }

    public void deleteQuestion(Integer id) {
        log.info("Запрос удаления вопроса с id = {}", id);

        questionRepository.deleteById(id);
    }

    public QuestionDto updateQuestionText(Integer id, QuestionDto questionDto) {
        log.info("Запрос изменения текста вопроса с id = {}: {}", id, questionDto);

        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Вопрос с id:%d не найден".formatted(id)));

        question.setContent(questionDto.getText());

        Question savedQuestion = questionRepository.save(question);
        return questionMapper.toQuestionDto(savedQuestion);
    }

    public QuestionDto updateQuestionType(Integer id, QuestionDto questionDto) {
        log.info("Запрос изменения типа вопроса с id = {}: {}", id, questionDto);

        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Вопрос с id:%d не найден".formatted(id)));

        question.setType(questionDto.getType());

        Question savedQuestion = questionRepository.save(question);
        return questionMapper.toQuestionDto(savedQuestion);
    }
}
