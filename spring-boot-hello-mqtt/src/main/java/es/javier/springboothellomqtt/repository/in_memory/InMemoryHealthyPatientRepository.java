package es.javier.springboothellomqtt.repository.in_memory;

import static es.javier.springboothellomqtt.constants.Profiles.MEMORY_PROFILE;

import es.javier.springboothellomqtt.model.entity.HealthyTemperatureMeasurementEntity;
import es.javier.springboothellomqtt.model.entity.TemperatureMeasurementEntity;
import es.javier.springboothellomqtt.repository.PatientRepository;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile(MEMORY_PROFILE)
public class InMemoryHealthyPatientRepository implements PatientRepository {
  private final ConcurrentLinkedQueue<TemperatureMeasurementEntity> database =
      new ConcurrentLinkedQueue<>();

  @Override
  public void saveTemperature(float temperature) {
    final var entity = new HealthyTemperatureMeasurementEntity();
    entity.setTemperature(temperature);
    entity.setTimestampToNow();
    database.add(entity);
  }

  @Override
  public List<TemperatureMeasurementEntity> readAllTemperatures() {
    return database.stream().toList();
  }
}
