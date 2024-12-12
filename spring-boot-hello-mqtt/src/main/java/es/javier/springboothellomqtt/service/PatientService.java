package es.javier.springboothellomqtt.service;

import es.javier.springboothellomqtt.model.dto.TemperatureMeasurementDto;
import java.util.List;

public interface PatientService {
  void saveHealthyPatientTemperature(float temperature);

  void saveSickPatientTemperature(float temperature);

  List<TemperatureMeasurementDto> getAllHealthyPatientTemperatures();

  List<TemperatureMeasurementDto> getAllSickPatientTemperatures();
}
