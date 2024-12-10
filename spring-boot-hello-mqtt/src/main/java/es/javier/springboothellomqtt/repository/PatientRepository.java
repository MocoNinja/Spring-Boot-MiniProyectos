package es.javier.springboothellomqtt.repository;

import es.javier.springboothellomqtt.model.TemperatureMeasurement;
import java.util.List;

public interface PatientRepository {
  void saveTemperature(final float temperature);

  List<TemperatureMeasurement> readAllTemperatures();
}
