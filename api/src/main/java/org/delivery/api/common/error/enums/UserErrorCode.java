package org.delivery.api.common.error.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.delivery.api.common.error.ErrorCodeIfs;

@AllArgsConstructor
@Getter
public enum UserErrorCode implements ErrorCodeIfs {
    USER_NOT_FOUND(400, 1404, "사용자를 찾을 수 없습니다.")
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;


}
