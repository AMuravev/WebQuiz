package engine.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnswerRequestModel implements AnswerList {
    private List<Integer> answer;
}
