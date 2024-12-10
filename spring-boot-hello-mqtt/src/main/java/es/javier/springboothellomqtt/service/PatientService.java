package es.javier.springboothellomqtt.service;

import es.javier.springboothellomqtt.model.TemperatureMeasurement;
import java.util.List;

public interface PatientService {
  void saveHealthyPatientTemperature(float temperature);

  void saveSickPatientTemperature(float temperature);

  List<TemperatureMeasurement> getAllHealthyPatientTemperatures();

  List<TemperatureMeasurement> getAllSickPatientTemperatures();
}
