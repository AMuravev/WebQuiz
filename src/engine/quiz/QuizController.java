package engine.quiz;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    QuizRequestModel quiz = new QuizRequestModel(
            "The Java Logo",
            "What is depicted on the Java logo?",
            List.of("Robot", "Tea leaf", "Cup of coffee", "Bug"),
            2);

    @GetMapping
    public ResponseEntity<QuizRequestModel> getQuiz() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(quiz, httpHeaders, HttpStatus.OK);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<AnswerResponseModel> compareAnswer(AnswerRequestModel answerRequestModel) {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        boolean result = quiz.getCorrectAnswer() == answerRequestModel.getAnswer();

        return new ResponseEntity<>(new AnswerResponseModel(result), httpHeaders, HttpStatus.OK);
    }
}
