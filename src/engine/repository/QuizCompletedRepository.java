package engine.repository;

import engine.entiry.QuizCompleted;
import engine.entiry.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizCompletedRepository extends JpaRepository<QuizCompleted, Long> {
    List<QuizCompleted> findByUser(User user);

    Page<QuizCompleted> findByUser(User user, Pageable pageable);
}
