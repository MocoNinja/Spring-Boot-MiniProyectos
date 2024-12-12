package es.javier.springboothellomqtt.handler;

import es.javier.springboothellomqtt.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class SickPatientDataHandler implements PatientHandler {
  private final Logger logger = LoggerFactory.getLogger(SickPatientDataHandler.class);
  private final PatientService patientService;

  public SickPatientDataHandler(final PatientService patientService) {
    this.patientService = patientService;
  }

  public void handle(final Message<String> message) {
    final float temperature = Float.parseFloat(message.getPayload());
    if (logger.isInfoEnabled()) {
      logger.info("La temperatura del enfermito es: %s".formatted(temperature));
    }
    patientService.saveSickPatientTemperature(temperature);
  }
}
