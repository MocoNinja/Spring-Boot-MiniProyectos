package es.javier.springboothellomqtt.rest;

import es.javier.springboothellomqtt.exception.MissingDataException;
import es.javier.springboothellomqtt.model.dto.TemperatureMeasurementDto;
import es.javier.springboothellomqtt.service.PatientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/patients")
public class PatientController {
  private final PatientService patientService;

  @Autowired
  public PatientController(final PatientService patientService) {
    this.patientService = patientService;
  }

  @GetMapping("/healthy")
  public ResponseEntity<List<TemperatureMeasurementDto>> getDataForHealthyPatients() {
    return ResponseEntity.ok(patientService.getAllHealthyPatientTemperatures());
  }

  @GetMapping("/healthy/latest")
  public ResponseEntity<TemperatureMeasurementDto> getLatestDataForHealthyPatients()
      throws MissingDataException {
    return ResponseEntity.ok(patientService.getLatestHealthyPatientTemperature());
  }

  @GetMapping("/sick")
  public ResponseEntity<List<TemperatureMeasurementDto>> getDataForSickPatients() {
    return ResponseEntity.ok(patientService.getAllSickPatientTemperatures());
  }

  @GetMapping("/sick/latest")
  public ResponseEntity<TemperatureMeasurementDto> getLatestDataForSickPatients()
      throws MissingDataException {
    return ResponseEntity.ok(patientService.getLatestSickPatientTemperature());
  }
}
