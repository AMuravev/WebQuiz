package engine.service;

import engine.entiry.Quiz;
import engine.model.QuizCreateDTO;
import engine.model.QuizViewDTO;

import java.util.List;
import java.util.Optional;

public interface IQuizService {

    Optional<Quiz> findById(long id);

    List<Quiz> findAll();

    QuizViewDTO save(QuizCreateDTO quiz);
}
