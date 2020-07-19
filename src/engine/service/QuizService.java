package engine.service;

import engine.entiry.Quiz;
import engine.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService implements IQuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Optional<Quiz> findById(long id) {
        return quizRepository.findById(id);
    }

    @Override
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz save(Quiz quiz) {
        return quizRepository.save(quiz);
    }
}
