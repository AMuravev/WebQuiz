package engine.quiz;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class QuizRequestModel {
    private String title;
    private String text;
    private List<String> options;
    private int correctAnswer;

    QuizRequestModel(String title, String text, List<String> options, int correctAnswer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    @JsonIgnore
    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
