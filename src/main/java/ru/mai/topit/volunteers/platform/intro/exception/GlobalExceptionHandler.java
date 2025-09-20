package ru.mai.topit.volunteers.platform.intro.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request) {
        return error(request, HttpStatus.BAD_REQUEST, "BAD_REQUEST", ex.getMessage(), null);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleUnexpected(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        return error(request, HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", "Internal server error", null);
    }

    private ResponseEntity<Object> error(HttpServletRequest request,
                                         HttpStatus status,
                                         String code,
                                         String message,
                                         Map<String, Object> details) {
        Map<String, Object> safeDetails = details != null ? details : new HashMap<>();
        log.error("Request failed: code={}, status={}, path={}, message={}, details={}",
                code,
                status.value(),
                request != null ? request.getRequestURI() : null,
                message,
                safeDetails);
        Map<String, Object> body = new HashMap<>();
        body.put("errorId", UUID.randomUUID().toString());
        body.put("timestamp", OffsetDateTime.now());
        body.put("status", status.value());
        body.put("code", code);
        body.put("message", message);
        body.put("path", request != null ? request.getRequestURI() : null);
        if (!safeDetails.isEmpty()) {
            body.put("details", safeDetails);
        }
        return ResponseEntity.status(status).body(body);
    }
}


