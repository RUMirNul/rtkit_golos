package com.rtkit.golos.core.service.implementation;


import com.rtkit.golos.core.access.PollQuestionRepository;
import com.rtkit.golos.core.access.PollRepo;
import com.rtkit.golos.core.access.QuestionRepository;
import com.rtkit.golos.core.dto.PollQuestionDto;
import com.rtkit.golos.core.dto.QuestionDto;
import com.rtkit.golos.core.entity.PollQuestion;
import com.rtkit.golos.core.entity.Question;
import com.rtkit.golos.core.event.NewQuestionCreatedEvent;
import com.rtkit.golos.core.exception.NotFoundException;
import com.rtkit.golos.core.mapper.QuestionMapper;
import com.rtkit.golos.core.service.QuestionService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final PollQuestionRepository pollQuestionRepository;
    private final QuestionMapper questionMapper;
    private final PollRepo pollRepo;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public List<PollQuestionDto> getAllQuestionsByPollId(int pollId) {
        log.info("Запрос получения вопросов по id опроса: {}", pollId);

        List<PollQuestion> pollQuestions = pollQuestionRepository.findAllByPollIdId(pollId);
        return questionMapper.toPollQuestionDtos(pollQuestions);
    }

    @Override
    public PollQuestionDto getPollQuestionById(int id) {

        PollQuestionDto pollQuestionDto = questionMapper.toPollQuestionDto(pollQuestionRepository.findByQuestionIdId(id));
        return pollQuestionDto;
    }

    @Override
    public QuestionDto getQuestionById(int id) {
        log.info("Запрос получения вопроса по id: {}", id);

        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Вопрос с id:%d не найден".formatted(id)));

        return questionMapper.toQuestionDto(question);
    }

    @Override
    public PollQuestionDto addQuestion(PollQuestionDto pollQuestionDto) {
        log.info("Запрос сохранения нового вопроса: {}", pollQuestionDto);

        Question question = questionMapper.toQuestion(pollQuestionDto.getQuestion());
        Question savedQuestion = questionRepository.save(question);
        log.info("Сохраненный Question: {}", savedQuestion);

        PollQuestion pollQuestion = new PollQuestion();
        pollQuestion.setPollId(pollRepo.getReferenceById(pollQuestionDto.getPollId()));
        pollQuestion.setQuestionId(savedQuestion);
        pollQuestion.setOrderInd(pollQuestionDto.getQuestion().getQuestionOrder().shortValue());
        PollQuestion savedPollQuestion = pollQuestionRepository.save(pollQuestion);
        log.info("Сохраненный PollQuestion: {}", savedPollQuestion);

        PollQuestionDto savedPollQuestionDto = new PollQuestionDto();
        savedPollQuestionDto.setQuestion(questionMapper.toQuestionDto(savedQuestion));
        savedPollQuestionDto.setPollId(pollQuestionDto.getPollId());
        savedPollQuestionDto.setId(savedPollQuestion.getId());
        log.info("Сопоставленный объект PollQuestionDto: {}", savedPollQuestionDto);

        applicationEventPublisher.publishEvent(new NewQuestionCreatedEvent(savedQuestion.getId()));

        return savedPollQuestionDto;
    }

    @Override
    public void deleteQuestion(Integer id) {
        log.info("Запрос удаления вопроса с id = {}", id);

        questionRepository.deleteById(id);
    }

    @Override
    public QuestionDto updateQuestionText(Integer id, QuestionDto questionDto) {
        log.info("Запрос изменения текста вопроса с id = {}: {}", id, questionDto);

        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Вопрос с id:%d не найден".formatted(id)));

        question.setContent(questionDto.getText());

        Question savedQuestion = questionRepository.save(question);
        return questionMapper.toQuestionDto(savedQuestion);
    }

    @Override
    public QuestionDto updateQuestionType(Integer id, QuestionDto questionDto) {
        log.info("Запрос изменения типа вопроса с id = {}: {}", id, questionDto);

        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Вопрос с id:%d не найден".formatted(id)));

        question.setType(questionDto.getType());

        Question savedQuestion = questionRepository.save(question);
        return questionMapper.toQuestionDto(savedQuestion);
    }
}
