package com.ileiwe.ileiwe.security.config;

import com.ileiwe.ileiwe.data.model.LearningParty;
import com.ileiwe.ileiwe.security.user.LearningPartyServiceImpl;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig
        extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final LearningPartyServiceImpl userDetails;

    @Autowired
    public  WebSecurityConfig(PasswordEncoder passwordEncoder, LearningPartyServiceImpl learningPartyService){
        this.passwordEncoder = passwordEncoder;
        this.userDetails = learningPartyService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter())
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .exceptionHandling()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
