package com.danifgx.opensource.collaborativetasks.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/**").permitAll() // Permitir acceso sin autenticación a endpoints específicos
                .anyRequest().authenticated() // Requiere autenticación para otros endpoints
                .and()
                .formLogin(); // Configuración de inicio de sesión basado en formulario (puedes personalizarlo según tus necesidades)
    }
}
