package ec.reto.wmpv.ws.service.sources;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"ec.wmpv.reto.persistence.service"})
@EnableJpaRepositories(basePackages = {"ec.wmpv.reto.persistence.repository"}, 
						entityManagerFactoryRef = "settingsEntityManager",
						transactionManagerRef = "settingsTransactionManager")
public class SettingsData {

	@Autowired
    private Environment env;
	
	@Bean
    public LocalContainerEntityManagerFactoryBean settingsEntityManager() {
        var em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(settingsDataSource());
        em.setPackagesToScan("ec.wmpv.reto.persistence.entity");

        HashMap<String, Object> properties = BaseData.entityProperties(em, env);
        properties.put("hibernate.dialect", env.getProperty("postgre.hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }
	
	@Bean
	public DataSource settingsDataSource() {
		AtomicReference<JndiDataSourceLookup> dataSourceLookup = new AtomicReference<JndiDataSourceLookup>(new JndiDataSourceLookup());
		return dataSourceLookup.get().getDataSource(env.getProperty("settings.jndi-name"));
	}
	
	@Bean
    public PlatformTransactionManager settingsTransactionManager() {
        var transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(settingsEntityManager().getObject());
        return transactionManager;
    }
}
