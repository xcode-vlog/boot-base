package org.delivery.api.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.api.common.error.ErrorCodeIfs;
import org.delivery.api.common.error.enums.ErrorCode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {

    private Integer resultCode;
    private String resultMessage;
    private String resultDescription;

    public static Result ok() {
        return Result.builder()
                .resultCode(ErrorCode.OK.getErrorCode())
                .resultMessage(ErrorCode.OK.getDescription())
                .resultDescription(ErrorCode.OK.getDescription())
                .build();
    }

    public static Result ERROR(ErrorCodeIfs errorCode) {
        return Result.builder()
                .resultCode(errorCode.getErrorCode())
                .resultMessage(errorCode.getDescription())
                .resultDescription(errorCode.getDescription())
                .build();
    }

    public static Result ERROR(ErrorCodeIfs errorCode, Throwable throwable) {
        return Result.builder()
                .resultCode(errorCode.getErrorCode())
                .resultMessage(errorCode.getDescription())
                .resultDescription(throwable.getLocalizedMessage())
                .build();
    }

    public static Result ERROR(ErrorCodeIfs errorCode, String description) {
        return Result.builder()
                .resultCode(errorCode.getErrorCode())
                .resultMessage(errorCode.getDescription())
                .resultDescription(description)
                .build();
    }


}
