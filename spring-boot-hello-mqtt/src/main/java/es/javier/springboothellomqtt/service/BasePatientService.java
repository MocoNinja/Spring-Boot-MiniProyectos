package es.javier.springboothellomqtt.service;

import es.javier.springboothellomqtt.exception.MissingDataException;
import es.javier.springboothellomqtt.mapper.TemperatureMeasurementMapper;
import es.javier.springboothellomqtt.model.dto.TemperatureMeasurementDto;
import es.javier.springboothellomqtt.repository.PatientRepository;
import java.util.List;

public abstract class BasePatientService implements PatientService {
  private final PatientRepository healthypatientRepository;
  private final PatientRepository sickpatientRepository;
  private final TemperatureMeasurementMapper temperatureMeasurementMapper;

  protected BasePatientService(
      final PatientRepository healthypatientRepository,
      final PatientRepository sickpatientRepository,
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

  @Override
  public TemperatureMeasurementDto getLatestHealthyPatientTemperature()
      throws MissingDataException {
    return healthypatientRepository
        .getLastTemperatureMeasurement()
        .map(temperatureMeasurementMapper::temperatureMeasurementDtoFromEntity)
        .orElseThrow(MissingDataException::new);
  }

  @Override
  public TemperatureMeasurementDto getLatestSickPatientTemperature() throws MissingDataException {
    return sickpatientRepository
        .getLastTemperatureMeasurement()
        .map(temperatureMeasurementMapper::temperatureMeasurementDtoFromEntity)
        .orElseThrow(MissingDataException::new);
  }
}
