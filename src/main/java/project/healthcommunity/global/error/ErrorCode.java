package project.healthcommunity.global.error;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ErrorCode {
    private int errorCode;
    private String message;

    public ErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Builder
    public ErrorCode(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
