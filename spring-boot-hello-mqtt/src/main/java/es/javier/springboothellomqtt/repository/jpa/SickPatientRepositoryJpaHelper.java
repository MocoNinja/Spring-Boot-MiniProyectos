package es.javier.springboothellomqtt.repository.jpa;

import static es.javier.springboothellomqtt.constants.Profiles.SQLITE_PROFILE;

import es.javier.springboothellomqtt.model.entity.SickTemperatureMeasurementEntity;
import java.util.UUID;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile(SQLITE_PROFILE)
public interface SickPatientRepositoryJpaHelper
    extends CrudRepository<SickTemperatureMeasurementEntity, UUID> {}