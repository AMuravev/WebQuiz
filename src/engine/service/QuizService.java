package engine.service;

import engine.Utils.AuthenticationFacade;
import engine.entiry.Quiz;
import engine.entiry.User;
import engine.exception.ResourceNotFoundException;
import engine.repository.QuizRepository;
import engine.security.UserPrincipalSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
    public Quiz save(Quiz quiz) {
        quiz.setUser(userService.getCurrentAuthUser());
        return quizRepository.save(quiz);
    }

    public void delete(long id) throws ResourceNotFoundException {

        User user = userService.getCurrentAuthUser();
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));

        if (quiz.getUser().equals(user)) {
            quizRepository.delete(quiz);
        } else {
            throw new AccessDeniedException("Authentication error");
        }
    }
}
