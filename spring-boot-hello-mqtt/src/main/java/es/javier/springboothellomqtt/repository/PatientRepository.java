package es.javier.springboothellomqtt.repository;

import es.javier.springboothellomqtt.model.entity.TemperatureMeasurementEntity;
import java.util.List;
import java.util.Optional;

public interface PatientRepository {
  Optional<TemperatureMeasurementEntity> getLastTemperatureMeasurement();

  void saveTemperature(final float temperature);

  List<TemperatureMeasurementEntity> readAllTemperatures();
}
