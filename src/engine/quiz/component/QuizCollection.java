package engine.quiz.component;

import engine.quiz.QuizRequestModel;

import java.util.ArrayList;
import java.util.List;

public class QuizCollection {

    private final List<QuizRequestModel> collection = new ArrayList<>();

    public void put(QuizRequestModel quizRequestModel) {
        collection.add(quizRequestModel);
    }

    public QuizRequestModel get(int id) {
        return collection.get(id - 1);
    }

    public List<QuizRequestModel> getAll() {
        return collection;
    }
}
