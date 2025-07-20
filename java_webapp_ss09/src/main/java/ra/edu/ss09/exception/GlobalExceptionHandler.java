package ra.edu.ss09.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        log.error("Đã xảy ra lỗi: {}", ex.getMessage(), ex);
        return ResponseEntity.status(500).body("Đã xảy ra lỗi trong hệ thống: " + ex.getMessage());
    }
}