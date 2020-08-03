package engine.service;

import engine.entiry.Quiz;
import engine.entiry.QuizCompleted;
import engine.entiry.User;
import engine.repository.QuizCompletedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizCompletedService {

    @Autowired
    private UserService userService;

    @Autowired
    private QuizCompletedRepository quizCompletedRepository;

    public void save(Quiz quiz) {
        User user = userService.getCurrentAuthUser();
        QuizCompleted quizCompleted = new QuizCompleted();
        quizCompleted.setUser(user);
        quizCompleted.setQuiz(quiz.getId());

        quizCompletedRepository.save(quizCompleted);
    }

    public List<QuizCompleted> findByCurrentUser() {
        User user = userService.getCurrentAuthUser();
        return quizCompletedRepository.findByUser(user);
    }

    public Page<QuizCompleted> findByCurrentUser(int pageNo) {
        User user = userService.getCurrentAuthUser();
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.by("completedAt").descending());
        return quizCompletedRepository.findByUser(user, pageable);
    }
}
