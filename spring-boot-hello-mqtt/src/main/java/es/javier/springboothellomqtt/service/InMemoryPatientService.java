package es.javier.springboothellomqtt.service;

import static es.javier.springboothellomqtt.constants.Profiles.MEMORY_PROFILE;

import es.javier.springboothellomqtt.mapper.TemperatureMeasurementMapper;
import es.javier.springboothellomqtt.model.dto.TemperatureMeasurementDto;
import es.javier.springboothellomqtt.repository.PatientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile(MEMORY_PROFILE)
public class InMemoryPatientService implements PatientService {
  private final PatientRepository healthypatientRepository;
  private final PatientRepository sickpatientRepository;
  private final TemperatureMeasurementMapper temperatureMeasurementMapper;

  @Autowired
  public InMemoryPatientService(
      final @Qualifier("inMemoryHealthyPatientRepository") PatientRepository
              healthypatientRepository,
      final @Qualifier("inMemorySickPatientRepository") PatientRepository sickpatientRepository,
      final TemperatureMeasurementMapper temperatureMeasurementMapper) {
    this.healthypatientRepository = healthypatientRepository;
    this.sickpatientRepository = sickpatientRepository;
    this.temperatureMeasurementMapper = temperatureMeasurementMapper;
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
  public List<TemperatureMeasurementDto> getAllHealthyPatientTemperatures() {
    return healthypatientRepository.readAllTemperatures().stream()
        .map(temperatureMeasurementMapper::temperatureMeasurementDtoFromEntity)
        .toList();
  }

  @Override
  public List<TemperatureMeasurementDto> getAllSickPatientTemperatures() {
    return sickpatientRepository.readAllTemperatures().stream()
        .map(temperatureMeasurementMapper::temperatureMeasurementDtoFromEntity)
        .toList();
  }
}
