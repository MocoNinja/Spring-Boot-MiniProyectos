package es.javier.springboothellomqtt.config.database.sqlite;

import static es.javier.springboothellomqtt.constants.Profiles.SQLITE_PROFILE;

import jakarta.persistence.EntityManagerFactory;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Profile(SQLITE_PROFILE)
@PropertySource({"classpath:database/sqlite/sqlite.properties", "classpath:application.properties"})
@EnableJpaRepositories(basePackages = "es.javier.springboothellomqtt.repository.jpa")
@EnableTransactionManagement
public class SqliteDatasource {

  @Value("${sqlite.url}")
  private String url;

  @Value("${sqlite.driverClassName}")
  private String driverClassName;

  @Value("${spring.jpa.hibernate.ddl-auto}")
  private String ddlAuto;

  @Value("${spring.jpa.show-sql}")
  private String showSql;

  @Value("${spring.jpa.database-platform}")
  private String databasePlatform;

  @Value("${sqlite.username}")
  private String username;

  @Value("${sqlite.password}")
  private String password;

  @Bean
  public DataSource dataSource() {
    final DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(driverClassName);
    dataSource.setUrl(url);

    dataSource.setUsername(username);
    dataSource.setPassword(password);
    return dataSource;
  }

  @Bean
  Properties jpaProperties() {
    final Properties jpaProperties = new Properties();
    jpaProperties.put("hibernate.dialect", databasePlatform);
    jpaProperties.put("hibernate.hbm2ddl.auto", ddlAuto);
    jpaProperties.put("hibernate.show_sql", showSql);
    return jpaProperties;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(true);

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("es.javier.springboothellomqtt.model.entity");
    factory.setDataSource(dataSource());
    factory.setJpaProperties(jpaProperties());

    return factory;
  }

  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    final JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory);
    return txManager;
  }
}
