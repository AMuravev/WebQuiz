package engine.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class QuizRequestModel {
    @JsonView(ViewModel.Public.class)
    private long id;

    @JsonView(ViewModel.Public.class)
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Text is required")
    @JsonView(ViewModel.Public.class)
    private String text;

    @NotNull
    @Size(min = 2)
    @JsonView(ViewModel.Public.class)
    private List<String> options;

    @JsonView(ViewModel.Internal.class)
    private List<Integer> answer = null;

    public QuizRequestModel() {
    }

    public QuizRequestModel(long id, String title, String text, List<String> options, List<Integer> answer) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
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

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = new ArrayList<>(answer);
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
