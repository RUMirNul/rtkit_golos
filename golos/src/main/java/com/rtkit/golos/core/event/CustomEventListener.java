package com.rtkit.golos.core.event;

import com.rtkit.golos.core.access.PollQuestionRepository;
import com.rtkit.golos.core.dto.QuestionAnswerDto;
import com.rtkit.golos.core.entity.PollQuestion;
import com.rtkit.golos.core.service.QuestionAnswerService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CustomEventListener {
    private final PollQuestionRepository pollQuestionRepository;
    private final QuestionAnswerService questionAnswerService;


    @EventListener
    public void handleNewQuestionCreatedEvent(NewQuestionCreatedEvent event) {
        PollQuestion pollQuestion = pollQuestionRepository.findByQuestionIdId(event.getNewQuestionId());
        List<PollQuestion> allQuestionsFromOnePoll = pollQuestionRepository.findAllByPollIdId(pollQuestion.getPollId().getId());
        for (PollQuestion question : allQuestionsFromOnePoll) {
            List<QuestionAnswerDto> answers =  questionAnswerService.getQuestionAnswersByPollQuestionId(question.getPollId().getId());
            for (QuestionAnswerDto answer : answers) {
                if (answer.getNextQuestionId() == null || answer.getNextQuestionId() == 0) {
                    answer.setNextQuestionId(event.getNewQuestionId());
                    questionAnswerService.updateQuestionAnswer(answer);
                }
            }
        }
    }
}
