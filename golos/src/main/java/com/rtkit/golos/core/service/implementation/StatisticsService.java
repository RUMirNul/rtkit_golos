package com.rtkit.golos.core.service.implementation;

import com.rtkit.golos.core.access.*;
import com.rtkit.golos.core.entity.Answer;
import com.rtkit.golos.core.entity.PollQuestion;
import com.rtkit.golos.core.entity.QuestionAnswer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.StatResultPoll;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class StatisticsService {
    private final PollQuestionRepository pollQuestionRepository;
    private final QuestionAnswerRepository questionAnswerRepository;
    private final UserAnswerRepository userAnswerRepository;
    private final ImageAnswerRepository imageAnswerRepository;
    private final TextAnswerRepository textAnswerRepository;
    private final AnswerRepository answerRepository;
    private final UserTextAnswerRepository userTextAnswerRepository;

    public List<StatResultPoll> formStatistics(int pollId) {
        List<PollQuestion> pollQuestions = pollQuestionRepository.findAllByPollIdId(pollId);
        List<StatResultPoll> pollAnswerDto = new ArrayList<>();
        for (PollQuestion question : pollQuestions) {
            for (QuestionAnswer answer : questionAnswerRepository.findAllByIdPollQuestionId(question.getQuestionId().getId())) {
                int answerId = answer.getId().getAnswerId();
                pollAnswerDto.add(new StatResultPoll(
                        question.getQuestionId().getContent(),
                        getAnswerContent(answerId),
                        userAnswerRepository.countByAnswerId(answerId),
                        question.getOrderInd()));
            }
        }
        return pollAnswerDto;
    }

    public String getAnswerContent(int answerId) {
        Answer answer = answerRepository.findById(answerId).orElseThrow();
        return switch (answer.getType()) {
            case USERTEXT -> userTextAnswerRepository.findById(answer.getId()).get().getPreparedText();
            case TEXT -> textAnswerRepository.findById(answer.getId()).get().getContent();
            case IMAGE -> imageAnswerRepository.findById(answer.getId()).get().getImagePath();
        };
    }
}
