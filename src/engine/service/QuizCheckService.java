package engine.service;

import engine.model.AnswerRequestModel;
import engine.model.MessageResponseModel;
import engine.model.QuizRequestModel;

public class QuizCheckService {

    MessageResponseModel error = new MessageResponseModel(false, "Wrong answer! Please, try again.");
    MessageResponseModel success = new MessageResponseModel(true, "Congratulations, you're right!");

    public MessageResponseModel compare(QuizRequestModel quiz, AnswerRequestModel answerRequest) {
        return quiz.getAnswer() == answerRequest.getAnswer() ? onSuccess() : onError();
    }

    private MessageResponseModel onSuccess() {
        return success;
    }

    private MessageResponseModel onError() {
        return error;
    }
}
