package ai.berry.viceversa.gloal.payload;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 에러 객체
 */
@Getter
public class ErrorResponse {

    /**
     * 에러 메시지
     */
    private final String message;

    /**
     * 발생 시간
     */
    private final LocalDateTime timestamp = LocalDateTime.now();

    public ErrorResponse(String message) {
        this.message = message;
    }
}
