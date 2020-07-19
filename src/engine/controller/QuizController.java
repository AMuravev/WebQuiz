package engine.controller;

import com.fasterxml.jackson.annotation.JsonView;
import engine.entiry.Quiz;
import engine.exception.ResourceNotFoundException;
import engine.model.*;
import engine.service.QuizCheckService;
import engine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;


@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizCheckService quizCheckService;

    @Autowired
    private QuizService quizService;

    //todo
    @Autowired
    Validator validator;

    @GetMapping("/{id}")
    @JsonView(ViewModel.Public.class)
    public ResponseEntity<Quiz> get(@PathVariable("id") long id) throws ResourceNotFoundException {

        Quiz quiz = quizService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));

        return ResponseEntity.ok().body(quiz);
    }

    @GetMapping
    @JsonView(ViewModel.Public.class)
    public ResponseEntity<List<Quiz>> getAll() {
        return ResponseEntity.ok().body(quizService.findAll());
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    @JsonView(ViewModel.Public.class)
    public ResponseEntity<Quiz> createViaUrlencoded(@Valid Quiz quizRequest) {
        return create(quizRequest);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    @JsonView(ViewModel.Public.class)
    public ResponseEntity<Quiz> createViaJson(@Valid @RequestBody Quiz quizRequest) {
        return create(quizRequest);
    }

    @PostMapping(
            path = "/{id}/solve",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<MessageResponseModel> solveQuizViaJson(@PathVariable("id") long id, @RequestBody AnswerRequestModel answerRequest) throws ResourceNotFoundException {
        return solveQuiz(id, answerRequest);
    }

    @PostMapping(
            path = "/{id}/solve",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<MessageResponseModel> solveQuizViaUrlencoded(@PathVariable("id") long id, AnswerRequestModel answerRequest) throws ResourceNotFoundException {
        return solveQuiz(id, answerRequest);
    }

    private ResponseEntity<Quiz> create(Quiz quizRequest) {
        Quiz quiz = quizService.save(quizRequest);

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(quiz, httpHeaders, HttpStatus.OK);
    }

    private ResponseEntity<MessageResponseModel> solveQuiz(long id, AnswerRequestModel answerRequest) throws ResourceNotFoundException {
        Quiz quiz = quizService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));

        MessageResponseModel messageResponse = quizCheckService.compare(quiz, answerRequest);

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(messageResponse, httpHeaders, HttpStatus.OK);
    }
}
