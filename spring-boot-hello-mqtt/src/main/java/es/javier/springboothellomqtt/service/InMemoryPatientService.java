package es.javier.springboothellomqtt.service;

import static es.javier.springboothellomqtt.constants.Profiles.MEMORY_PROFILE;

import es.javier.springboothellomqtt.mapper.TemperatureMeasurementMapper;
import es.javier.springboothellomqtt.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile(MEMORY_PROFILE)
public class InMemoryPatientService extends BasePatientService {
  @Autowired
  public InMemoryPatientService(
      final @Qualifier("inMemoryHealthyPatientRepository") PatientRepository
              healthypatientRepository,
      final @Qualifier("inMemorySickPatientRepository") PatientRepository sickpatientRepository,
      final TemperatureMeasurementMapper temperatureMeasurementMapper) {
    super(healthypatientRepository, sickpatientRepository, temperatureMeasurementMapper);
  }
}
