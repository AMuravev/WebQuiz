package engine.service;

import engine.Utils.ListUtils;
import engine.model.AnswerRequestModel;
import engine.model.MessageResponseModel;
import engine.model.QuizRequestModel;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QuizCheckService {

    MessageResponseModel error = new MessageResponseModel(false, "Wrong answer! Please, try again.");
    MessageResponseModel success = new MessageResponseModel(true, "Congratulations, you're right!");

    public MessageResponseModel compare(QuizRequestModel quiz, AnswerRequestModel answerRequest) {
        List<Integer> a = ListUtils.getSortedNotNull(quiz.getAnswer());
        List<Integer> b = ListUtils.getSortedNotNull(answerRequest.getAnswer());

        return Objects.equals(a, b) ? onSuccess() : onError();
    }

    private MessageResponseModel onSuccess() {
        return success;
    }

    private MessageResponseModel onError() {
        return error;
    }
}
