package es.javier.springboothellomqtt.exception;

import org.springframework.http.HttpStatus;

public class MissingDataException extends CustomException {

  public MissingDataException() {
    super("Expected data was not found", HttpStatus.PRECONDITION_FAILED);
  }

  public MissingDataException(final String message) {
    super(message, HttpStatus.PRECONDITION_FAILED);
  }
}
