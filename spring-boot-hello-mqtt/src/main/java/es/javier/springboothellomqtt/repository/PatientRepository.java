package es.javier.springboothellomqtt.repository;

import es.javier.springboothellomqtt.model.entity.TemperatureMeasurementEntity;
import java.util.List;

public interface PatientRepository {
  void saveTemperature(final float temperature);

  List<TemperatureMeasurementEntity> readAllTemperatures();
}
