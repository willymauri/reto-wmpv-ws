package ec.reto.wmpv.ws.service.sources;

import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @version 1.0
 * @autor william.patino [Date: 19 ago. 2022]
 **/
public class BaseData implements Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static HashMap<String, Object> entityProperties(LocalContainerEntityManagerFactoryBean em, Environment env) {
        HashMap<String, Object> properties = new HashMap<>();
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        properties.put("hibernate.jdbc.lob.non_contextual_creation", env.getProperty("hibernate.jdbc.lob.non_contextual_creation"));
        properties.put("hibernate.generate_statistics", env.getProperty("hibernate.generate_statistics"));
        properties.put("hibernate.use_sql_comments", env.getProperty("hibernate.use_sql_comments"));
        properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        properties.put("hibernate.ddl-auto", env.getProperty("hibernate.ddl-auto"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        return properties;
    }
}
