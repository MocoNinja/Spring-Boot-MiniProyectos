package es.javier.springboothellomqtt.repository.sqlite;

import static es.javier.springboothellomqtt.constants.Profiles.SQLITE_PROFILE;

import es.javier.springboothellomqtt.model.entity.HealthyTemperatureMeasurementEntity;
import es.javier.springboothellomqtt.model.entity.TemperatureMeasurementEntity;
import es.javier.springboothellomqtt.repository.PatientRepository;
import es.javier.springboothellomqtt.repository.jpa.HealthyPatientRepositoryJpaHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile(SQLITE_PROFILE)
public class SqliteHealthyPatientRepository implements PatientRepository {

  @PersistenceContext private final EntityManager entityManager;
  private final HealthyPatientRepositoryJpaHelper healthyPatientRepositoryJpaHelper;

  @Autowired
  public SqliteHealthyPatientRepository(
      final EntityManager entityManager,
      final HealthyPatientRepositoryJpaHelper healthyPatientRepositoryJpaHelper) {
    this.entityManager = entityManager;
    this.healthyPatientRepositoryJpaHelper = healthyPatientRepositoryJpaHelper;
  }

  @Override
  @Transactional
  public void saveTemperature(float temperature) {
    final HealthyTemperatureMeasurementEntity entity = new HealthyTemperatureMeasurementEntity();
    entity.setTemperature(temperature);
    healthyPatientRepositoryJpaHelper.save(entity);
  }

  @Override
  public List<TemperatureMeasurementEntity> readAllTemperatures() {
    final List<TemperatureMeasurementEntity> results = new ArrayList<>();
    healthyPatientRepositoryJpaHelper.findAll().iterator().forEachRemaining(results::add);
    return results;
  }
}
