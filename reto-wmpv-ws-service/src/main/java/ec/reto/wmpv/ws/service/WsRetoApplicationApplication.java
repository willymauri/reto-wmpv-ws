package ec.reto.wmpv.ws.service;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ec.reto.wmpv.ws.noc.service.auth.AuthorizationFilter;

/**
 * @version 1.0
 * @autor william.patino [Date: 06 apr. 2022]
 **/
@SpringBootApplication
public class WsRetoApplicationApplication extends SpringBootServletInitializer {
    private static final Class<WsRetoApplicationApplication> applicationClass = WsRetoApplicationApplication.class;

    public static void main(String[] args) {
        SpringApplication.run(WsRetoApplicationApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    @Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity security) throws Exception {
        	security.csrf().disable()
        	.addFilterAfter(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
        	.authorizeRequests().antMatchers(HttpMethod.POST, "/rest/wmpv/login").permitAll()
			.anyRequest().authenticated();
        }
    }

}
