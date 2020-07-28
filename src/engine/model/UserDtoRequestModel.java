package engine.model;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class UserDtoRequestModel {

    @Pattern(regexp = "^[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,6}$")
    private String email;

    @NotBlank(message = "Password is required")
    @Length(min = 5)
    private String password;
}
