package engine.service;

import engine.Utils.ListUtils;
import engine.model.AnswerList;
import engine.model.MessageResponseModel;

import java.util.*;

public class QuizCheckService {

    MessageResponseModel error = new MessageResponseModel(false, "Wrong answer! Please, try again.");
    MessageResponseModel success = new MessageResponseModel(true, "Congratulations, you're right!");

    public MessageResponseModel compare(AnswerList answerA, AnswerList answerB) {
        List<Integer> a = ListUtils.getSortedNotNull(answerA.getAnswer());
        List<Integer> b = ListUtils.getSortedNotNull(answerB.getAnswer());

        return Objects.equals(a, b) ? onSuccess() : onError();
    }

    private MessageResponseModel onSuccess() {
        return success;
    }

    private MessageResponseModel onError() {
        return error;
    }
}
