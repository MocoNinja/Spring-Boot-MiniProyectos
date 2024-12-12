package es.javier.springboothellomqtt.service;

import static es.javier.springboothellomqtt.constants.Profiles.SQLITE_PROFILE;

import es.javier.springboothellomqtt.mapper.TemperatureMeasurementMapper;
import es.javier.springboothellomqtt.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile(SQLITE_PROFILE)
public class SqlitePatientService extends BasePatientService {

  @Autowired
  public SqlitePatientService(
      final @Qualifier("sqliteHealthyPatientRepository") PatientRepository healthypatientRepository,
      final @Qualifier("sqliteSickPatientRepository") PatientRepository sickpatientRepository,
      final TemperatureMeasurementMapper temperatureMeasurementMapper) {
    super(healthypatientRepository, sickpatientRepository, temperatureMeasurementMapper);
  }
}
