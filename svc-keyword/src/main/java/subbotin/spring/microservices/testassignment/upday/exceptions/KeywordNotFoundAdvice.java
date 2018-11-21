package subbotin.spring.microservices.testassignment.upday.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class KeywordNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(KeywordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String keywordNotFoundHandler(KeywordNotFoundException ex) {
        return ex.getMessage();
    }
}
