package engine.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuizDtoResponseModel {

    private long id;

    private String title;

    private String text;

    private List<String> options;
    
}
