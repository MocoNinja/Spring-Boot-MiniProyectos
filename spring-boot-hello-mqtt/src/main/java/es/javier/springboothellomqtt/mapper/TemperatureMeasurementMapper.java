package es.javier.springboothellomqtt.mapper;

import es.javier.springboothellomqtt.model.dto.TemperatureMeasurementDto;
import es.javier.springboothellomqtt.model.entity.TemperatureMeasurementEntity;
import org.springframework.stereotype.Service;

@Service
public class TemperatureMeasurementMapper {
  public TemperatureMeasurementDto temperatureMeasurementDtoFromEntity(
      final TemperatureMeasurementEntity entity) {
    return new TemperatureMeasurementDto(entity.getTemperature(), entity.getTimestamp());
  }
}
