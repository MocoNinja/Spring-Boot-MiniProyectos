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
- [MQTT](https://docs.spring.io/spring-integration/reference/mqtt.html)
- [QOS](https://www.hivemq.com/blog/mqtt-essentials-part-6-mqtt-quality-of-service-levels/)
- [Shared Subs](https://www.hivemq.com/blog/mqtt5-essentials-part7-shared-subscriptions/)
- [Retention](https://forum.cedalo.com/t/retention-of-messages-and-removal-etc/568/7)
- [Clean sessions](http://www.steves-internet-guide.com/mqtt-clean-sessions-example/)

## Learning tags

- MQTT
- PROFILES
- CUSTOM CONFIGURATION
- JPA