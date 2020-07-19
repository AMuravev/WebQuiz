package engine.controller;

import engine.exception.ResourceNotFoundException;
import engine.model.MessageResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new MessageResponseModel(false, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

}
