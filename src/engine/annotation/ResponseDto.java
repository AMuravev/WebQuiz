package engine.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ResponseDto {
    Class<?> value();

    boolean list() default false;
}
