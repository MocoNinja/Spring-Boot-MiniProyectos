package es.javier.springboothellomqtt.repository.in_memory;

import static es.javier.springboothellomqtt.constants.Profiles.MEMORY_PROFILE;

import es.javier.springboothellomqtt.model.entity.SickTemperatureMeasurementEntity;
import es.javier.springboothellomqtt.model.entity.TemperatureMeasurementEntity;
import es.javier.springboothellomqtt.repository.PatientRepository;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedDeque;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile(MEMORY_PROFILE)
public class InMemorySickPatientRepository implements PatientRepository {
  private final ConcurrentLinkedDeque<TemperatureMeasurementEntity> database =
      new ConcurrentLinkedDeque<>();

  @Override
  public Optional<TemperatureMeasurementEntity> getLastTemperatureMeasurement() {
    return Optional.ofNullable(database.getLast());
  }

  @Override
  public void saveTemperature(float temperature) {
    final var entity = new SickTemperatureMeasurementEntity();
    entity.setTemperature(temperature);
    entity.setTimestampToNow();
    database.add(entity);
  }

  @Override
  public List<TemperatureMeasurementEntity> readAllTemperatures() {
    return database.stream().toList();
  }
}
