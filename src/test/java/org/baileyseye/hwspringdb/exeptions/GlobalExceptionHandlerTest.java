package org.baileyseye.hwspringdb.exeptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private WebRequest webRequest;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleGlobalException_ShouldReturnInternalServerError() {
        Exception exception = new Exception("Test Exception");
        ResponseEntity<?> responseEntity = globalExceptionHandler.handleGlobalException(exception, webRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("An error occurred: Test Exception", responseEntity.getBody());
    }

    @Test
    void handleNullPointerException_ShouldReturnBadRequest() {
        NullPointerException nullPointerException = new NullPointerException("Test NullPointerException");
        ResponseEntity<?> responseEntity = globalExceptionHandler.handleNullPointerException
                (nullPointerException, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("NullPointerException caught: Test NullPointerException",
                responseEntity.getBody());
    }
}