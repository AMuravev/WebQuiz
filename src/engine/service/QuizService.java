package engine.service;

import engine.entiry.Quiz;
import engine.entiry.User;
import engine.exception.ResourceNotFoundException;
import engine.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService implements IQuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserService userService;

    @Override
    public Optional<Quiz> findById(long id) {
        return quizRepository.findById(id);
    }

    @Override
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    @Override
    public Page<Quiz> findAll(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 10);
        return quizRepository.findAll(pageable);
    }

    @Override
    public Quiz save(Quiz quiz) {
        quiz.setUser(userService.getCurrentAuthUser());
        return quizRepository.save(quiz);
    }

    @Override
    public void delete(long id) throws ResourceNotFoundException {

        User user = userService.getCurrentAuthUser();
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));

        if (quiz.getUser() != null && quiz.getUser().equals(user)) {
            quizRepository.delete(quiz);
        } else {
            throw new AccessDeniedException("Authentication error");
        }
    }
}
