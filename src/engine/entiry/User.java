package engine.entiry;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class User {

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder(11);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @Pattern(regexp = "^[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,6}$")
    private String email;

    @NotBlank(message = "Password is required")
    @Length(min = 5)
    private String password;

    @Value("#{T(java.util.Collections).emptyList()}")
    @OneToMany(mappedBy = "user")
    @ElementCollection(targetClass = Quiz.class)
    private List<Quiz> quizzes;

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
