package es.javier.springboothellomqtt.repository;

import es.javier.springboothellomqtt.model.TemperatureMeasurement;
import org.springframework.stereotype.Repository;

import java.time.Clock;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Repository
public class InMemorySickPatientRepository implements  PatientRepository{
    private final ConcurrentLinkedQueue<TemperatureMeasurement> database = new ConcurrentLinkedQueue<>();


    @Override
    public void saveTemperature(float temperature) {
        database.add(new TemperatureMeasurement(temperature, Instant.now(Clock.systemUTC())));
    }

    @Override
    public List<TemperatureMeasurement> readAllTemperatures() {
        return database.stream().toList();
    }
}
