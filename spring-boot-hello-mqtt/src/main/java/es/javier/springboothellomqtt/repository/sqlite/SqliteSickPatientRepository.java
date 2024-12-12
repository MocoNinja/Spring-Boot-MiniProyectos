package es.javier.springboothellomqtt.repository.sqlite;

import static es.javier.springboothellomqtt.constants.Profiles.SQLITE_PROFILE;

import es.javier.springboothellomqtt.model.entity.SickTemperatureMeasurementEntity;
import es.javier.springboothellomqtt.model.entity.TemperatureMeasurementEntity;
import es.javier.springboothellomqtt.repository.PatientRepository;
import es.javier.springboothellomqtt.repository.jpa.SickPatientRepositoryJpaHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile(SQLITE_PROFILE)
public class SqliteSickPatientRepository implements PatientRepository {
  @PersistenceContext private final EntityManager entityManager;
  private final SickPatientRepositoryJpaHelper sickPatientRepositoryJpaHelper;

  @Autowired
  public SqliteSickPatientRepository(
      final EntityManager entityManager,
      final SickPatientRepositoryJpaHelper sickPatientRepositoryJpaHelper) {
    this.entityManager = entityManager;
    this.sickPatientRepositoryJpaHelper = sickPatientRepositoryJpaHelper;
  }

  @Override
  public Optional<TemperatureMeasurementEntity> getLastTemperatureMeasurement() {
    return this.sickPatientRepositoryJpaHelper
        .findLatestRecord()
        .map(maybeRecord -> Optional.ofNullable(maybeRecord).orElse(null));
  }

  @Override
  @Transactional
  public void saveTemperature(float temperature) {
    final SickTemperatureMeasurementEntity entity = new SickTemperatureMeasurementEntity();
    entity.setTemperature(temperature);
    sickPatientRepositoryJpaHelper.save(entity);
  }

  @Override
  public List<TemperatureMeasurementEntity> readAllTemperatures() {
    final List<TemperatureMeasurementEntity> entities = new ArrayList<>();
    sickPatientRepositoryJpaHelper.findAll().iterator().forEachRemaining(entities::add);
    return entities;
  }
}
