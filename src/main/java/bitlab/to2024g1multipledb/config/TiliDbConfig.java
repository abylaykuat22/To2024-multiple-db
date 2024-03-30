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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "entityManagerTiliDb",
    basePackages = {
        "bitlab.to2024g1multipledb.tilidb.repository"
    }
)
public class TiliDbConfig {

  @Bean(name = "tiliDataSource")
  @ConfigurationProperties(prefix = "spring.tili.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "entityManagerTiliDb")
  public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
      EntityManagerFactoryBuilder entityBuilder,
      @Qualifier("tiliDataSource") DataSource dataSource
  ) {
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.hbm2ddl.auto", "update");

    return entityBuilder.dataSource(dataSource)
        .properties(properties)
        .packages("bitlab.to2024g1multipledb.tilidb.entity")
        .persistenceUnit("tili")
        .build();
  }

  @Bean(name = "transactionManagerTiliDb")
  public PlatformTransactionManager transactionManager(
      @Qualifier("entityManagerTiliDb") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }
}
