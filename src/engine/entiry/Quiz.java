package engine.entiry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import engine.model.AnswerList;
import engine.model.ViewModel;
import lombok.AccessLevel;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ViewModel.Public.class)
    private long id;

    @NotBlank(message = "Title is required")
    @JsonView(ViewModel.Public.class)
    private String title;

    @NotBlank(message = "Text is required")
    @JsonView(ViewModel.Public.class)
    private String text;

    @NotNull
    @Size(min = 2)
    @ElementCollection(targetClass = String.class)
    @JsonView(ViewModel.Public.class)
    private List<String> options;

    @Value("#{T(java.util.Collections).emptyList()}")
    @ElementCollection(targetClass = Integer.class)
    @JsonView(ViewModel.Internal.class)
    private List<Integer> answer;
}
