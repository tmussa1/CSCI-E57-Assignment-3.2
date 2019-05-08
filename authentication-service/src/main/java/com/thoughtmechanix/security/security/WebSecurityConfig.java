package com.thoughtmechanix.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public UserDetailsService userDetailsService(){

        UserDetailsService userDetailsService = null;

        try {
            userDetailsService = super.userDetailsServiceBean();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userDetailsService;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("tofik.mussa").password("{noop}11559301").roles("USER")
                .and()
                .withUser("risqua.mussa").password("{noop}11314321").roles("ADMIN", "USER");
    }
}