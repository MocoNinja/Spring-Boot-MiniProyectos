package es.javier.springboothellomqtt.service;

import es.javier.springboothellomqtt.model.TemperatureMeasurement;
import es.javier.springboothellomqtt.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryPatientService implements PatientService {
    private final PatientRepository healthypatientRepository;
    private final PatientRepository sickpatientRepository;

    @Autowired
    public InMemoryPatientService(
            final @Qualifier("inMemoryHealthyPatientRepository") PatientRepository healthypatientRepository,
            final @Qualifier("inMemorySickPatientRepository") PatientRepository sickpatientRepository) {
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
