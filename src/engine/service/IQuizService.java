package engine.service;

import engine.entiry.Quiz;
import engine.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IQuizService {

    Optional<Quiz> findById(long id);

    List<Quiz> findAll();

    Page<Quiz> findAll(int pageNo);

    Quiz save(Quiz quiz);

    void delete(long id) throws ResourceNotFoundException;
}
