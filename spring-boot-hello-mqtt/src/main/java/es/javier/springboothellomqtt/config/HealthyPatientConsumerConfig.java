package es.javier.springboothellomqtt.config;

import es.javier.springboothellomqtt.handler.PatientHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class HealthyPatientConsumerConfig {
  private static final String CLIENT_ID = "spring-boot-client-healthy-consumer";
  private final PatientHandler handler;

  @Value("${mqtt.topic.healthy}")
  private String topic;

  @Autowired
  public HealthyPatientConsumerConfig(@Qualifier("healthyPatientDataHandler")PatientHandler handler) {
    this.handler = handler;
  }



  @Bean
  public MessageProducer mqttProducerHealthyPatient(
      @Qualifier("mqttClientFactory") MqttPahoClientFactory factory,
      @Qualifier("inputMqttChannel") MessageChannel channel) {
    MqttPahoMessageDrivenChannelAdapter adapter =
        new MqttPahoMessageDrivenChannelAdapter(CLIENT_ID, factory, topic);
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
