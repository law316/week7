package net.ikwa.week7project.test;

import net.ikwa.week7project.exception.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleRuntime_shouldReturnBadRequest() {
        RuntimeException ex = new RuntimeException("Error occurred");

        ResponseEntity<ErrorResponse> response =
                handler.handleRuntime(ex);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Error occurred", response.getBody().getMessage());
        assertNotNull(response.getBody().getTimestamp());
    }
}