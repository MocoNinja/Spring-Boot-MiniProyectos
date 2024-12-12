package es.javier.springboothellomqtt.rest.advice;

import es.javier.springboothellomqtt.exception.CustomError;
import es.javier.springboothellomqtt.exception.MissingDataException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CommonControllerHandler {

  @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
  @ExceptionHandler(MissingDataException.class)
  @ResponseBody
  CustomError handleMissingDataException(final HttpServletRequest req, final Exception ex) {
    return new CustomError(ex.getMessage(), req.getServletPath());
  }
}
