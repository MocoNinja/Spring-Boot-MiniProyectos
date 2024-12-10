package es.javier.springboothellomqtt.repository.in_memory;

import es.javier.springboothellomqtt.model.TemperatureMeasurement;
import es.javier.springboothellomqtt.repository.PatientRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.Clock;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Repository
@Profile("!sqlite")
public class InMemoryHealthyPatientRepository implements PatientRepository {
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