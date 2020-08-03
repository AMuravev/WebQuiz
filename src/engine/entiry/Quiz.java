package engine.entiry;

import engine.model.AnswerList;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
public class Quiz implements AnswerList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Text is required")
    private String text;

    @NotNull
    @Size(min = 2)
    @ElementCollection(targetClass = String.class)
    private List<String> options;

    @Value("#{T(java.util.Collections).emptyList()}")
    @ElementCollection(targetClass = Integer.class)
    private List<Integer> answer;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;
//
//    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "quiz")
//    private List<QuizCompleted> completions = new ArrayList<>();
}
