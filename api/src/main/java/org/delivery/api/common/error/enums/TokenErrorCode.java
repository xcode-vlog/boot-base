package org.delivery.api.common.error.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.delivery.api.common.error.ErrorCodeIfs;

@AllArgsConstructor
@Getter
public enum TokenErrorCode implements ErrorCodeIfs {
    TOKEN_EXCEPTION(400, 2000, "알수없는 토큰 에러"),
    INVALID_TOKEN(400, 2001, "유효하지 않은 토큰"),
    EXPIRED_TOKEN(400, 2002, "만료된 토큰"),
    AUTHORIZATION_TOKEN_NOT_FOUND(400, 2003, "인증된 header 토큰이 없습니다.")
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;


}
