package org.delivery.api.domain.token.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.enums.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.ifs.TokenHelperIfs;
import org.delivery.api.domain.token.model.TokenDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenHelperIfs tokenHelper;

    public TokenDto issueAccessToken(Long userId) {
        var data = new HashMap<String, Object>();
        data.put("userId", userId);
        return tokenHelper.issuedAccessToken(data);
    }

    public TokenDto issueRefreshToken(Long userId) {
        var data = new HashMap<String, Object>();
        data.put("userId", userId);
        return tokenHelper.issueRefreshToken(data);
    }

    public Long validationToken(String token) {
        var map = tokenHelper.validationTokenWithThrow(token);
        var userId = map.get("userId");

        Objects.requireNonNull(userId, () -> {throw new ApiException(ErrorCode.NULL_POINT);});

        return Long.parseLong(userId.toString());
    }
}
