package engine.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class QuizDtoRequestModel {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Text is required")
    private String text;

    @NotNull
    @Size(min = 2)
    private List<String> options;

    @Value("#{T(java.util.Collections).emptyList()}")
    private List<Integer> answer;
}
