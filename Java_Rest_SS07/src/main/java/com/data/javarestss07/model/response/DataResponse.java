package com.data.javarestss07.model.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataResponse<T> {
    private T data;
    private HttpStatus status;
}