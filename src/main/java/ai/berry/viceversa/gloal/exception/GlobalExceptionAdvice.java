package ai.berry.viceversa.gloal.exception;

import ai.berry.viceversa.gloal.payload.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchElementException.class)
    public ErrorResponse templateParseException(NoSuchElementException e) {

        log.error(e.getMessage());
        return new ErrorResponse(e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse exception(Exception ex) {

        log.error("알 수 없는 오류 발생");
        ex.printStackTrace();
        log.error("알 수 없는 오류 발생");

        return new ErrorResponse("서버 처리 중 알 수 없는 오류가 발생 했습니다.");
    }
}
