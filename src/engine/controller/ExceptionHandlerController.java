package engine.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;

@ControllerAdvice
public class ExceptionHandlerController {
    //    @ExceptionHandler({ConstraintViolationException.class,MethodArgumentNotValidException.class})
//    @ExceptionHandler(ConstraintViolationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public HashMap<String, String> handleIndexOutOfBoundsException(Exception e) {
//        HashMap<String, String> response = new HashMap<>();
//        response.put("message", "NOT_FOUND_MESSAGE");
//        response.put("error", "e.getClass().getSimpleName()");
//        return response;
//    }
}
