package es.javier.springboothellomqtt.config.mqtt;

import es.javier.springboothellomqtt.handler.PatientHandler;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
@PropertySource("classpath:mqtt/sick_patient.properties")
public class SickPatientConsumerConfig {
  private final PatientHandler handler;

  @Value("${mqtt.sick.id}")
  private String id;

  @Value("${mqtt.sick.topic}")
  private String topic;

  @Value("${mqtt.sick.qos}")
  private int qos;

  @Autowired
  public SickPatientConsumerConfig(@Qualifier("sickPatientDataHandler") PatientHandler handler) {
    this.handler = handler;
  }

  @Bean
  public MessageProducer mqttProducerSickPatient(
      @Qualifier("mqttClientFactory") MqttPahoClientFactory factory,
      @Qualifier("inputMqttChannel") MessageChannel channel) {
    MqttPahoMessageDrivenChannelAdapter adapter =
        new MqttPahoMessageDrivenChannelAdapter(
            id.concat("_").concat(UUID.randomUUID().toString()), factory, topic);
    adapter.setOutputChannel(channel);
    adapter.setQos(qos);
    return adapter;
  }

  @Bean
  @ServiceActivator(inputChannel = "inputMqttChannel")
  public MessageHandler sickPatientHandler() {
    return msg -> {
      // TODO: converter y demás
      Message<String> parsedMsg = (Message<String>) msg;
      handler.handle(parsedMsg);
    };
  }
}
