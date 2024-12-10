package es.javier.springboothellomqtt.handler;

import es.javier.springboothellomqtt.service.PatientService;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class SickPatientDataHandler implements PatientHandler {
  private final PatientService patientService;

  public SickPatientDataHandler(final PatientService patientService) {
    this.patientService = patientService;
  }

  public void handle(final Message<String> message) {
    final float temperature = Float.parseFloat(message.getPayload());
    System.out.println("La temperatura del enfermito es: %s".formatted(temperature));
    patientService.saveSickPatientTemperature(temperature);
  }
}
