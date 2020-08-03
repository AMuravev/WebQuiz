package engine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import engine.entiry.Quiz;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonPropertyOrder({"id", "completedAt"})
public class QuizCompletedDtoResponseModel {

    @JsonProperty("id")
    private long quiz;

    private Date completedAt;

//    public void setQuiz(Quiz quiz) {
//        this.quiz = quiz.getId();
//    }
}
