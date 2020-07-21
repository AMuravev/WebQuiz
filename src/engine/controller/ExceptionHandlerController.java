package engine.controller;

import engine.exception.ResourceNotFoundException;
import engine.model.MessageResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new MessageResponseModel(false, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

}
