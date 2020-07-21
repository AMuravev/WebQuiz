package engine.Utils;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface OutputDTO {
    Class<?> value();

    boolean list() default false;
}
