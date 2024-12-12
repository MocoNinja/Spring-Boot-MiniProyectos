package es.javier.springboothellomqtt.handler;

import es.javier.springboothellomqtt.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class HealthyPatientDataHandler implements PatientHandler {
  private final Logger logger = LoggerFactory.getLogger(HealthyPatientDataHandler.class);
  private final PatientService patientService;

  public HealthyPatientDataHandler(final PatientService patientService) {
    this.patientService = patientService;
  }

  public void handle(final Message<String> message) {
    final float temperature = Float.parseFloat(message.getPayload());
    if (logger.isInfoEnabled()) {
      logger.info("La temperatura del sanote es: %s".formatted(temperature));
    }
    patientService.saveHealthyPatientTemperature(temperature);
  }
}
