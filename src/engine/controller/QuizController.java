package engine.controller;

import com.fasterxml.jackson.annotation.JsonView;
import engine.model.*;
import engine.repository.QuizRepository;
import engine.service.QuizCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizCheckService quizCheckService;

    //todo
    @Autowired
    Validator validator;

    @GetMapping("/{id}")
    @JsonView(ViewModel.Public.class)
    public ResponseEntity<QuizRequestModel> get(@PathVariable("id") int id) {
        QuizRequestModel quiz = quizRepository.find(id);

        if (quiz == null) {
            return ResponseEntity.notFound().build();
        }

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>((QuizRequestModel) quizRepository.find(id), httpHeaders, HttpStatus.OK);
    }

    @GetMapping
    @JsonView(ViewModel.Public.class)
    public ResponseEntity<List<QuizRequestModel>> getAll() {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(quizRepository.findAll(), httpHeaders, HttpStatus.OK);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    @JsonView(ViewModel.Public.class)
    public ResponseEntity<QuizRequestModel> createViaUrlencoded(@Valid QuizRequestModel quizRequest) {
        return create(quizRequest);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    @JsonView(ViewModel.Public.class)
    public ResponseEntity<QuizRequestModel> createViaJson(@Valid @RequestBody QuizRequestModel quizRequest) {
        return create(quizRequest);
    }

    @PostMapping(
            path = "/{id}/solve",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<MessageResponseModel> solveQuizViaJson(@PathVariable("id") int id, @RequestBody AnswerRequestModel answerRequest) {
        return solveQuiz(id, answerRequest);
    }

    @PostMapping(
            path = "/{id}/solve",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<MessageResponseModel> solveQuizViaUrlencoded(@PathVariable("id") int id, AnswerRequestModel answerRequest) {
        return solveQuiz(id, answerRequest);
    }

    private ResponseEntity<QuizRequestModel> create(QuizRequestModel quizRequest) {
        QuizRequestModel quiz = quizRepository.create(quizRequest);

        if (quiz == null) {
            return ResponseEntity.notFound().build();
        }

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(quiz, httpHeaders, HttpStatus.OK);
    }

    private ResponseEntity<MessageResponseModel> solveQuiz(int id, AnswerRequestModel answerRequest) {
        QuizRequestModel quiz = quizRepository.find(id);

        if (quiz == null) {
            return ResponseEntity.notFound().build();
        }

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        MessageResponseModel messageResponse = quizCheckService.compare(quiz, answerRequest);

        return new ResponseEntity<>(messageResponse, httpHeaders, HttpStatus.OK);
    }
}
