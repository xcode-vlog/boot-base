package org.delivery.api.common.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.api.common.error.ErrorCodeIfs;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Api<T> {

    private Result result;

    @Valid
    private T body;

    public static <T> Api OK(T data) {
        Api<T> api = new Api<T>();
        api.body = data;
        api.result = Result.ok();
        return api;
    }

    public static Api ERROR(Result result) {
        Api api = new Api();
        api.result = result;
        return api;
    }

    public static Api ERROR(ErrorCodeIfs error) {
        Api api = new Api();
        api.result = Result.ERROR(error);
        return api;
    }
    public static Api ERROR(ErrorCodeIfs error, Throwable throwable) {
        Api api = new Api();
        api.result = Result.ERROR(error, throwable);
        return api;
    }

    public static Api ERROR(ErrorCodeIfs error, String description) {
        Api api = new Api();
        api.result = Result.ERROR(error, description);
        return api;
    }
}
