package com.alexeyshekhonov.gbshop.configs;

import com.alexeyshekhonov.gbshop.services.DaoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Profile("dao")
@EnableWebSecurity
public class DaoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DaoUserService daoUserService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
                authorizeRequests()
                .antMatchers(HttpMethod.GET, "/**").permitAll()
                .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                .anyRequest().hasAnyRole("USER", "ADMIN")
                .and()
                .formLogin().defaultSuccessUrl("/");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(daoUserService);
        return authenticationProvider;
    }
}
