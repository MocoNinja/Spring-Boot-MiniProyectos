package es.javier.springboothellomqtt.handler;

import org.springframework.messaging.Message;

public interface PatientHandler {
  void handle(final Message<String> message);
}
