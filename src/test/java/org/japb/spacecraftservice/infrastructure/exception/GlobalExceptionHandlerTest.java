package org.japb.spacecraftservice.infrastructure.exception;

import org.japb.spacecraftservice.domain.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHandleResourceNotFoundException() {
        ResourceNotFoundException ex = new ResourceNotFoundException("Resource not found");
        ResponseEntity<String> responseEntity = exceptionHandler.handleResourceNotFoundException(ex);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(responseEntity.getBody()).isEqualTo("Resource not found");
    }

    @Test
    public void testHandleIllegalArgumentException() {
        IllegalArgumentException ex = new IllegalArgumentException("Invalid argument");
        ResponseEntity<String> responseEntity = exceptionHandler.handleIllegalArgumentException(ex);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody()).isEqualTo("Invalid argument");
    }

    @Test
    public void testHandleGlobalException() {
        Exception ex = new Exception("Unexpected error");
        ResponseEntity<String> responseEntity = exceptionHandler.handleGlobalException(ex);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(responseEntity.getBody()).isEqualTo("Unexpected error");
    }

    @Test
    public void testHandleGlobalExceptionWithNullMessage() {
        Exception ex = new Exception(); // Exception without message
        ResponseEntity<String> responseEntity = exceptionHandler.handleGlobalException(ex);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        //assertThat(responseEntity.getBody()).isEqualTo("Internal Server Error");
    }
}
