package com.rtkit.golos.core.handler;

import com.rtkit.golos.core.exception.NotFoundException;
import com.rtkit.golos.core.exception.UserAlreadyExistException;
import com.rtkit.golos.core.web.response.BaseErrorWebResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Глобальный обработчик исключений для контроллеров. Обрабатывает исключения и возвращает соответствующий HTTP-статус и тело ответа.
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    /**
     * Обработчик исключения NotFoundException. Возвращает ответ с HTTP-статусом NOT_FOUND (404).
     *
     * @param e Исключение NotFoundException.
     * @return ResponseEntity с телом ответа в виде BaseWebResponse.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BaseErrorWebResponse> handleNotFoundException(@NonNull final NotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseErrorWebResponse(createErrorMessage(e)));
    }

    /**
     * Обработчик исключения UserAlreadyExistException. Возвращает ответ с HTTP-статусом CONFLICT (409).
     *
     * @param e Исключение NotFoundException.
     * @return ResponseEntity с телом ответа в виде BaseWebResponse.
     */
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<BaseErrorWebResponse> handleUserAlreadyExistException(@NonNull final UserAlreadyExistException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new BaseErrorWebResponse(createErrorMessage(e)));
    }


    /**
     * Создает строку с сообщением об ошибке для логирования.
     *
     * @param exception Исключение.
     * @return Строка с сообщением об ошибке.
     */
    private String createErrorMessage(Exception exception) {
        final String message = exception.getMessage();
        log.error("Ошибка: ", exception);
        return message;
    }
}
