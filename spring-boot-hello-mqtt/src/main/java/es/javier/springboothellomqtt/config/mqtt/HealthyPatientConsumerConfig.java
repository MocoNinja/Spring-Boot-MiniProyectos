package es.javier.springboothellomqtt.config.mqtt;

import es.javier.springboothellomqtt.handler.PatientHandler;
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
@PropertySource("classpath:mqtt/healthy_patient.properties")
public class HealthyPatientConsumerConfig {
  private final PatientHandler handler;

  @Value("${mqtt.healthy.id}")
  private String id;

  @Value("${mqtt.healthy.topic}")
  private String topic;

  @Value("${mqtt.healthy.qos}")
  private int qos;

  @Autowired
  public HealthyPatientConsumerConfig(
      @Qualifier("healthyPatientDataHandler") PatientHandler handler) {
    this.handler = handler;
  }

  @Bean
  public MessageProducer mqttProducerHealthyPatient(
      @Qualifier("mqttClientFactory") MqttPahoClientFactory factory,
      @Qualifier("inputMqttChannel") MessageChannel channel) {
    MqttPahoMessageDrivenChannelAdapter adapter =
        new MqttPahoMessageDrivenChannelAdapter(id, factory, topic);
    adapter.setQos(qos);
    adapter.setOutputChannel(channel);
    return adapter;
  }

  @Bean
  @ServiceActivator(inputChannel = "inputMqttChannel")
  public MessageHandler healthyPatientHandler() {
    return msg -> {
      // TODO: converter y demás
      Message<String> parsedMsg = (Message<String>) msg;
      handler.handle(parsedMsg);
    };
  }
}
