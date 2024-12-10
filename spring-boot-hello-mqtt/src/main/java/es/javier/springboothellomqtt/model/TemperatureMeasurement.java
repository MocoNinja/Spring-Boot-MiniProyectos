package es.javier.springboothellomqtt.model;

import java.time.Instant;

public record TemperatureMeasurement(float temperature, Instant timestamp) {}
