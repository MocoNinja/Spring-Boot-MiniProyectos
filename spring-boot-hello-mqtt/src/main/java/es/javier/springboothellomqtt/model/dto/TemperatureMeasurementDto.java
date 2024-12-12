package es.javier.springboothellomqtt.model.dto;

import java.time.Instant;

public record TemperatureMeasurementDto(float temperature, Instant timestamp) {}
