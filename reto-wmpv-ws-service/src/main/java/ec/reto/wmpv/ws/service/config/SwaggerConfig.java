package ec.reto.wmpv.ws.service.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.google.common.base.Predicates;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2)
			      .apiInfo(apiInfo())
			      .select()
			      .apis(RequestHandlerSelectors.any())
			      .paths(PathSelectors.any())
			      .paths(Predicates.not(PathSelectors.regex("/error.*")))
			      .build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfo("Microservicio - WMPV", "Servicio Web", "v1.0", "TERMS OF SERVICE URL",
				new Contact("William", "william.patino", "localhost@wmpv.ec"), "Licencia del API",
				"", Collections.emptyList());
	}
}
