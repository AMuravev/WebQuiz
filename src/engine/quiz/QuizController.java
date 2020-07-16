package engine.quiz;

import engine.quiz.component.QuizCollection;
import engine.quiz.conf.ConfigurationApp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizCollection quizCollection;
    final AtomicLong counter = new AtomicLong();

    QuizController() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationApp.class);
        this.quizCollection = context.getBean(QuizCollection.class);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizRequestModel> get(@PathVariable int id) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(quizCollection.get(id), httpHeaders, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<QuizRequestModel>> getAll() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(quizCollection.getAll(), httpHeaders, HttpStatus.OK);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<QuizRequestModel> create(QuizRequestModel quizRequestModel) {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        QuizRequestModel quiz = new QuizRequestModel(
                counter.incrementAndGet(),
                quizRequestModel.getTitle(),
                quizRequestModel.getText(),
                quizRequestModel.getOptions(),
                quizRequestModel.getAnswer()
        );

        quizCollection.put(quiz);

        return new ResponseEntity<>(quiz, httpHeaders, HttpStatus.OK);
    }

//    @PostMapping(
//            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
//            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
//    )
//    public ResponseEntity<AnswerResponseModel> compareAnswer(AnswerRequestModel answerRequestModel) {
//
//        final HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        boolean result = quiz.getCorrectAnswer() == answerRequestModel.getAnswer();
//
//        return new ResponseEntity<>(new AnswerResponseModel(result), httpHeaders, HttpStatus.OK);
//    }
}
