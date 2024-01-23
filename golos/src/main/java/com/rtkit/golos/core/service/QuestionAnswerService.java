package com.rtkit.golos.core.service;

import com.rtkit.golos.core.access.BranchingAnswerRepository;
import com.rtkit.golos.core.access.ImageAnswerRepository;
import com.rtkit.golos.core.access.QuestionAnswerRepository;
import com.rtkit.golos.core.access.TextAnswerRepository;
import com.rtkit.golos.core.dto.AddAnswerDto;
import com.rtkit.golos.core.dto.AddQuestionAnswerDto;
import com.rtkit.golos.core.dto.AnswerDto;
import com.rtkit.golos.core.dto.QuestionAnswerDto;
import com.rtkit.golos.core.entity.*;
import com.rtkit.golos.core.mapper.AnswerMapper;
import com.rtkit.golos.core.mapper.QuestionAnswerMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class QuestionAnswerService {

    private QuestionAnswerRepository questionAnswerRepository;
    private TextAnswerRepository textAnswerRepository;
    private ImageAnswerRepository imageAnswerRepository;
    private BranchingAnswerRepository branchingAnswerRepository;

    private AnswerService answerService;
    private AnswerMapper answerMapper;
    private QuestionAnswerMapper questionAnswerMapper;

    @Transactional
    public QuestionAnswerDto addTypedQuestionAnswer(Integer pollQuestionId, AddAnswerDto answer) {
        log.info("Сохранение ответа на вопрос: {}, с pollQuestionId = {}", answer, pollQuestionId);
        AnswerDto answerDto = answerMapper.toDto(answer);
        Answer savedAnswer = answerService.saveAnswer(answerDto);

        saveQuestionAnswer(pollQuestionId, savedAnswer.getId(), (short) 0);

        return saveTypedAnswer(savedAnswer, answer.getContent());

    }

    @Transactional
    protected QuestionAnswer saveQuestionAnswer(Integer pollQuestionId, Integer answerId, Short answerOrder) {
        log.info("Сохранение сущности QuestionAnswer: pollQuestionId = {}, answerId = {}, answerOrder = {}", pollQuestionId, answerId, answerOrder);
        QuestionAnswerId questionAnswerId = new QuestionAnswerId();
        questionAnswerId.setPollQuestionId(pollQuestionId);
        questionAnswerId.setAnswerId(answerId);

        QuestionAnswer questionAnswer = new QuestionAnswer();
        questionAnswer.setId(questionAnswerId);
        questionAnswer.setAnswerOrder((short) answerOrder);
        QuestionAnswer savedQuestionAnswer = questionAnswerRepository.save(questionAnswer);
        log.info("Сохраненная сущность QuestionAnswer: {}", savedQuestionAnswer);

        return savedQuestionAnswer;
    }

    @Transactional
    protected QuestionAnswerDto saveTypedAnswer(Answer answer, String content) {
        log.info("Сохраение ответа: {}, с контентом: {}", answer, content);

        switch (answer.getType()) {
            case TEXT -> {
                TextAnswer textAnswer = new TextAnswer();
                textAnswer.setAnswer(answer);
                textAnswer.setContent(content);
                TextAnswer savedTextAnswer = textAnswerRepository.save(textAnswer);
                log.info("Сохранён текстовый ответ: {}", savedTextAnswer);
                return questionAnswerMapper.toDto(savedTextAnswer);
            }
            case IMAGE -> {
                ImageAnswer imageAnswer = new ImageAnswer();
                imageAnswer.setAnswer(answer);
                imageAnswer.setImagePath(content);
                ImageAnswer savedImageAnswer = imageAnswerRepository.save(imageAnswer);
                log.info("Сохранён ответ с изображением: {}", savedImageAnswer);
                return questionAnswerMapper.toDto(savedImageAnswer);
            }
            case BRANCHING -> {
                BranchingAnswer branchingAnswer = new BranchingAnswer();
                branchingAnswer.setAnswer(answer);
                branchingAnswer.setOrderInd(Short.parseShort(content));
                BranchingAnswer savedBranchingAnswer = branchingAnswerRepository.save(branchingAnswer);
                log.info("Сохранён ответ с ветвлением: {}", savedBranchingAnswer);
                return questionAnswerMapper.toDto(savedBranchingAnswer);
            }
            //TODO usertext
        }

        return new QuestionAnswerDto();
    }

    @Transactional
    public List<QuestionAnswerDto> addNewQuestionAnswer(AddQuestionAnswerDto request) {
        log.info("Сохранение ответов на вопрос: {}", request);

        List<QuestionAnswerDto> savedAnswers = new ArrayList<>();
        for (AddAnswerDto newAnswer : request.getAnswers()) {
            QuestionAnswerDto savedAnswer = addTypedQuestionAnswer(request.getPollQuestionId(), newAnswer);
            savedAnswers.add(savedAnswer);
        }
        log.info("Сохраненные ответы: {}", savedAnswers);

        return savedAnswers;
    }

    @Transactional
    public List<QuestionAnswerDto> getQuestionAnswersByPollQuestionId(int pollQuestionId) {
        log.info("Получение ответов на вопрос по pollQuestionId = {}", pollQuestionId);

        List<QuestionAnswerDto> result = new ArrayList<>();
        List<QuestionAnswer> founded = questionAnswerRepository.findAllByIdPollQuestionId(pollQuestionId);
        log.info("Найденные ответы: {}", founded);

        for (QuestionAnswer qa : founded) {
            int answerId = qa.getId().getAnswerId();
            AnswerDto answerDto = answerService.getAnswer(answerId);

            switch (answerDto.getType()) {
                case TEXT ->
                        result.add(questionAnswerMapper.toDto(textAnswerRepository.getReferenceById(answerDto.getId())));
                case IMAGE ->
                        result.add(questionAnswerMapper.toDto(imageAnswerRepository.getReferenceById(answerDto.getId())));
                case BRANCHING ->
                        result.add(questionAnswerMapper.toDto(branchingAnswerRepository.getReferenceById(answerDto.getId())));
            }
        }
        log.info("Типизированные ответы на вопрос: {}", result);

        return result;
    }

    @Transactional
    public void deleteQuestionAnswer(int answerId) {
        log.info("Удаление ответа с id = {}", answerId);
        AnswerDto answerDto = answerService.getAnswer(answerId);

        log.info("Удаление типизированного ответа.");
        switch (answerDto.getType()) {
            case TEXT -> textAnswerRepository.deleteById(answerDto.getId());
            case IMAGE -> imageAnswerRepository.deleteById(answerDto.getId());
            case BRANCHING -> branchingAnswerRepository.deleteById(answerDto.getId());
        }
        QuestionAnswer questionAnswer = questionAnswerRepository.findByIdAnswerId(answerDto.getId());
        log.info("Найденный QuestionAnswer для удаления: {}", questionAnswer);
        questionAnswerRepository.delete(questionAnswer);
        answerService.deleteAnswer(answerDto.getId());
    }

    @Transactional
    public QuestionAnswerDto updateQuestionAnswer(QuestionAnswerDto newQuestionAnswer) {
        log.info("Запрос обновления ответа: {}", newQuestionAnswer);
        AnswerDto updatableAnswer = answerService.getAnswer(newQuestionAnswer.getId());

        if (updatableAnswer.getType().equals(newQuestionAnswer.getType())) {
            switch (updatableAnswer.getType()) {
                case TEXT -> {
                    log.info("Обновление текстового ответа.");
                    TextAnswer textAnswer = textAnswerRepository.getReferenceById(updatableAnswer.getId());
                    textAnswer.setContent(newQuestionAnswer.getContent());
                    QuestionAnswerDto updatedTextAnswer = questionAnswerMapper.toDto(textAnswerRepository.save(textAnswer));
                    log.info("Обновленный текстовый ответ: {}", updatedTextAnswer);
                    return updatedTextAnswer;
                }
                case IMAGE -> {
                    log.info("Обновление ответа с изображением.");
                    ImageAnswer imageAnswer = imageAnswerRepository.getReferenceById(updatableAnswer.getId());
                    imageAnswer.setImagePath(newQuestionAnswer.getContent());
                    QuestionAnswerDto updatedImageAnswer = questionAnswerMapper.toDto(imageAnswerRepository.save(imageAnswer));
                    log.info("Обновленный ответ с изображением: {}", updatedImageAnswer);
                    return updatedImageAnswer;
                }
                case BRANCHING -> {
                    log.info("Обновление ответа с ветвлением.");
                    BranchingAnswer branchingAnswer = branchingAnswerRepository.getReferenceById(updatableAnswer.getId());
                    branchingAnswer.setOrderInd(Short.parseShort(newQuestionAnswer.getContent()));
                    QuestionAnswerDto updatedBranchingAnswer = questionAnswerMapper.toDto(branchingAnswerRepository.save(branchingAnswer));
                    log.info("Обновленный ответ с ветвлением: {}", updatedBranchingAnswer);
                    return updatedBranchingAnswer;
                }
            }
        } else {
            switch (updatableAnswer.getType()) {
                case TEXT -> {
                    log.info("Удаление текстового ответа с id = {}", updatableAnswer.getId());
                    textAnswerRepository.deleteById(updatableAnswer.getId());
                }
                case IMAGE -> {
                    log.info("Удаление ответа с изображением с id = {}", updatableAnswer.getId());
                    imageAnswerRepository.deleteById(updatableAnswer.getId());
                }
                case BRANCHING -> {
                    log.info("Удаление ответа с ветвлением с id = {}", updatableAnswer.getId());
                    branchingAnswerRepository.deleteById(updatableAnswer.getId());
                }
            }

            updatableAnswer.setType(newQuestionAnswer.getType());
            Answer updatedAnswer = answerService.saveAnswer(updatableAnswer);
            return saveTypedAnswer(updatedAnswer, newQuestionAnswer.getContent());
        }

        return new QuestionAnswerDto();
    }
}