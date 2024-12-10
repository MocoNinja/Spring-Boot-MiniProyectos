# Spring Boot MQTT Integration

## Setup

Credentials are meant to be read from an .env file in the source of the project with these values:
```txt
mqtt_username=<fillme>
mqtt_password=<fillme>
```

## Data

In resources, a *nodered* flow is attached. It is a little hardcoded to my mosquitto installation for now.

## References

- [Database Config](https://docs.spring.io/spring-data/jpa/reference/repositories/create-instances.html)
- [MQTT] (https://docs.spring.io/spring-integration/reference/mqtt.html)

## Learning tags

- MQTT
- PROFILES
- DEPENDENCY INJECTION
- CUSTOM CONFIGURATION