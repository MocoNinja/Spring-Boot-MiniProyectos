package es.javier.springboothellomqtt.model.entity;

import java.time.Instant;

public interface TemperatureMeasurementEntity {
  Float getTemperature();

  Instant getTimestamp();
}
