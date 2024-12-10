package es.javier.springboothellomqtt.service;

import es.javier.springboothellomqtt.model.TemperatureMeasurement;
import es.javier.springboothellomqtt.repository.PatientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("sqlite")
public class SqlitePatientService implements PatientService {
  private final PatientRepository healthypatientRepository;
  private final PatientRepository sickpatientRepository;

  @Autowired
  public SqlitePatientService(
      final @Qualifier("sqliteHealthyPatientRepository") PatientRepository healthypatientRepository,
      final @Qualifier("sqliteSickPatientRepository") PatientRepository sickpatientRepository) {
    this.healthypatientRepository = healthypatientRepository;
    this.sickpatientRepository = sickpatientRepository;
  }

  @Override
  public void saveHealthyPatientTemperature(float temperature) {
    healthypatientRepository.saveTemperature(temperature);
  }

  @Override
  public void saveSickPatientTemperature(float temperature) {
    sickpatientRepository.saveTemperature(temperature);
  }

  @Override
  public List<TemperatureMeasurement> getAllHealthyPatientTemperatures() {
    return healthypatientRepository.readAllTemperatures();
  }

  @Override
  public List<TemperatureMeasurement> getAllSickPatientTemperatures() {
    return sickpatientRepository.readAllTemperatures();
  }
}
