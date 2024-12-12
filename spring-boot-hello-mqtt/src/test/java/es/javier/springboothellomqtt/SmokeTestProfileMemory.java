package es.javier.springboothellomqtt;

import static org.assertj.core.api.Assertions.assertThat;

import es.javier.springboothellomqtt.service.InMemoryPatientService;
import es.javier.springboothellomqtt.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"spring.profiles.active=memory"})
class SmokeTestProfileMemory {

  @Autowired private PatientService patientService;

  @Test
  void contextLoads() {
    assertThat(patientService).isNotNull();
    assertThat(patientService).isInstanceOf(InMemoryPatientService.class);
  }
}
