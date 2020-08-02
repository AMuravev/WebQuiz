package engine.controller;

import engine.annotation.ResponseDto;
import engine.annotation.RequestDto;
import engine.entiry.Quiz;
import engine.entiry.QuizCompleted;
import engine.exception.ResourceNotFoundException;
import engine.model.*;
import engine.service.QuizCheckService;
import engine.service.QuizCompletedService;
import engine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizCompletedService quizCompletedService;

    @Autowired
    private QuizCheckService quizCheckService;

    @Autowired
    private QuizService quizService;

    @GetMapping("/completed")
    @ResponseDto(value = QuizCompletedDtoResponseModel.class, list = true)
    public ResponseEntity<Page<QuizCompleted>> getCompleted(@RequestParam(defaultValue = "0") int page) {

        Page<QuizCompleted> quizCompleted = quizCompletedService.findByCurrentUser(page);

        return ResponseEntity.ok().body(quizCompleted);
    }

    @GetMapping("/{id}")
    @ResponseDto(QuizDtoResponseModel.class)
    public ResponseEntity<Quiz> get(@PathVariable("id") long id) throws ResourceNotFoundException {

        Quiz quiz = quizService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));

        return ResponseEntity.ok().body(quiz);
    }

    @GetMapping
    @ResponseDto(value = QuizDtoResponseModel.class, list = true)
    public ResponseEntity<Page<Quiz>> getAll(@RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok().body(quizService.findAll(page));
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseDto(QuizDtoResponseModel.class)
    public ResponseEntity<Quiz> createViaUrlencoded(Quiz quiz) {
        return create(quiz);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseDto(QuizDtoResponseModel.class)
    public ResponseEntity<Quiz> createViaJson(@RequestDto(QuizDtoRequestModel.class) Quiz quiz) {
        return create(quiz);
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteQuiz(@PathVariable("id") long id) throws ResourceNotFoundException {
        quizService.delete(id);
        return null;
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

        if (messageResponse.isSuccess()) {
            quizCompletedService.save(quiz);
        }

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(messageResponse, httpHeaders, HttpStatus.OK);
    }
}
