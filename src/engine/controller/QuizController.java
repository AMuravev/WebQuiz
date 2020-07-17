package engine.controller;

import engine.model.*;
import engine.repository.QuizRepository;
import engine.service.QuizCheckService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private QuizRepository quizRepository;

    @Autowired
    private QuizCheckService quizCheckService;

    @GetMapping("/{id}")
    public ResponseEntity<QuizRequestModel> get(@PathVariable("id") int id) {
        QuizRequestModel quiz = quizRepository.find(id);

        if (quiz == null) {
            return ResponseEntity.notFound().build();
        }

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(quizRepository.find(id), httpHeaders, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<QuizRequestModel>> getAll() {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(quizRepository.findAll(), httpHeaders, HttpStatus.OK);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<QuizRequestModel> create(QuizRequestModel quizRequest) {

        QuizRequestModel quiz = quizRepository.create(quizRequest);

        if (quiz == null) {
            return ResponseEntity.notFound().build();
        }

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(quiz, httpHeaders, HttpStatus.OK);
    }

    @PostMapping(
            path = "/{id}/solve",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<MessageResponseModel> solveQuiz(@PathVariable("id") int id, AnswerRequestModel answerRequest) {

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
