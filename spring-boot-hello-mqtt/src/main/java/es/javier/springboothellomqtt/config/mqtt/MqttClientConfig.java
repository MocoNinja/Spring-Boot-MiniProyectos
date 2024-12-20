package es.javier.springboothellomqtt.config.mqtt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.messaging.MessageChannel;

@Configuration
@PropertySource("classpath:mqtt/mqtt.properties")
public class MqttClientConfig {

  @Value("${mqtt.protocol}")
  private String protocol;

  @Value("${mqtt.hostname}")
  private String host;

  @Value("${mqtt.port}")
  private String port;

  @Value("${mqtt.username}")
  private String username;

  @Value("${mqtt.password}")
  private String password;

  @Value("${mqtt.clean_session}")
  private boolean cleanSession;

  @Bean
  public MqttConnectOptions mqttConnectOptions() {
    MqttConnectOptions options = new MqttConnectOptions();
    options.setServerURIs(new String[] {assembleUrl()});
    options.setCleanSession(true);
    options.setUserName(username);
    options.setPassword(password.toCharArray());
    options.setCleanSession(cleanSession);
    return options;
  }

  @Bean
  public MqttPahoClientFactory mqttClientFactory() {
    DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
    factory.setConnectionOptions(mqttConnectOptions());
    return factory;
  }

  @Bean
  public MessageChannel inputMqttChannel() {
    return new DirectChannel();
  }

  private String assembleUrl() {
    return "%s://%s:%s".formatted(protocol, host, port);
  }
}
