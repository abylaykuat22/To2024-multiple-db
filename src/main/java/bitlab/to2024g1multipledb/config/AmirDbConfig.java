package bitlab.to2024g1multipledb.config;

import jakarta.persistence.EntityManagerFactory;
import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "entityManagerAmirDb",
    basePackages = {
        "bitlab.to2024g1multipledb.amirdb.repository"
    }
)
public class AmirDbConfig {

  @Primary
  @Bean(name = "amirDataSource")
  @ConfigurationProperties(prefix = "spring.amir.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }

  @Primary
  @Bean(name = "entityManagerAmirDb")
  public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
      EntityManagerFactoryBuilder builder,
      @Qualifier("amirDataSource") DataSource dataSource
  ) {
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.hbm2ddl.auto", "update");

    return builder.dataSource(dataSource)
        .properties(properties)
        .packages("bitlab.to2024g1multipledb.amirdb.entity")
        .persistenceUnit("amir")
        .build();
  }

  /**
   * При подключении двух баз. В одном их конфигурационных классов обязательно должен быть бин с
   * наименованием "transactionManager"
   */
  @Primary
  @Bean(name = "transactionManager")
  public PlatformTransactionManager transactionManager(
      @Qualifier("entityManagerAmirDb") EntityManagerFactory entityManagerFactory
  ) {
    return new JpaTransactionManager(entityManagerFactory);
  }
}
