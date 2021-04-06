package atmosphere.web.spring.controller;

import atmosphere.error.NotFountMusicReview;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MusicReviewAdvice {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String errorHandler(NotFountMusicReview e) {
        return e.getMessage();
    }
}
