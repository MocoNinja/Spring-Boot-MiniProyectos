package es.javier.springboothellomqtt.model.entity;

import jakarta.persistence.*;
import java.time.Clock;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "sick_patient_measurements")
public class SickTemperatureMeasurementEntity implements TemperatureMeasurementEntity {
  @GeneratedValue(strategy = GenerationType.UUID)
  @Id
  private UUID id;

  @Column(name = "temperature", nullable = false)
  private Float temperature;

  @Column(name = "timestamp", nullable = false)
  private Instant timestamp;

  public Float getTemperature() {
    return temperature;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public void setTemperature(final Float temperature) {
    this.temperature = temperature;
  }

  @PrePersist
  @PreUpdate
  public void setTimestampToNow() {
    this.timestamp = Instant.now(Clock.systemUTC());
  }
}
