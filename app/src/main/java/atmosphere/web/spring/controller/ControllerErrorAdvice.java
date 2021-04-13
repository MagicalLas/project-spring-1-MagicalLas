package atmosphere.web.spring.controller;

import atmosphere.error.AlreadyExistRecommendationBox;
import atmosphere.error.NotFountMusicRecommendationBox;
import atmosphere.error.NotFountMusicReview;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerErrorAdvice {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String errorHandler(NotFountMusicReview e) {
        return e.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String errorHandler(AlreadyExistRecommendationBox e) {
        return e.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String errorHandler(NotFountMusicRecommendationBox e) {
        return e.getMessage();
    }
}
