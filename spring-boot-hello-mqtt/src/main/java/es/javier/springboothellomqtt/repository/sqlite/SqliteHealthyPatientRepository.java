package es.javier.springboothellomqtt.repository.sqlite;

import es.javier.springboothellomqtt.model.TemperatureMeasurement;
import es.javier.springboothellomqtt.model.entity.HealthyTemperatureMeasurementEntity;
import es.javier.springboothellomqtt.repository.PatientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("sqlite")
public class SqliteHealthyPatientRepository implements PatientRepository {

  @PersistenceContext private final EntityManager entityManager;

  @Autowired
  public SqliteHealthyPatientRepository(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  @Transactional
  public void saveTemperature(float temperature) {
    final HealthyTemperatureMeasurementEntity entity =
        new HealthyTemperatureMeasurementEntity(temperature);
    if (entity.getId() == null) {
      entityManager.persist(entity);
    } else {
      entityManager.merge(entity);
    }
  }

  @Override
  public List<TemperatureMeasurement> readAllTemperatures() {
    final Query query =
        entityManager.createQuery("SELECT e from HealthyTemperatureMeasurementEntity e");
    final List<HealthyTemperatureMeasurementEntity> entities = query.getResultList();
    return entities.stream()
        .map(entity -> new TemperatureMeasurement(entity.getTemperature(), entity.getTimestamp()))
        .toList();
  }
}
