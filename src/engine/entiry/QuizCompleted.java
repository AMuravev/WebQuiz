package engine.entiry;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class QuizCompleted {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @Column(name = "quizID")
    private long quiz;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "taskID", nullable = false)
//    private Quiz quiz;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date completedAt;
}
