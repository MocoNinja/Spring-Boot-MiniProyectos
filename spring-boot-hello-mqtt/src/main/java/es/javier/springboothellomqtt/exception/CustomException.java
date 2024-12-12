package es.javier.springboothellomqtt.exception;

import org.springframework.http.HttpStatus;

public abstract class CustomException extends Exception {
  private String message;
  private HttpStatus status;

  CustomException(final String message, final HttpStatus status) {
    super(message);
    this.message = message;
    this.status = status;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
