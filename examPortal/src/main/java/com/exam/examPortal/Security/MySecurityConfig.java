package com.exam.examPortal.Security;

import com.exam.examPortal.Service.Imp.UserDetailsServiceImpl;
import com.exam.examPortal.filter.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class MySecurityConfig{
    @Autowired

     AuthFilter authFilter;
    @Autowired
    private JwtAuthenticationEntryPoint unathorizedHandler;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public UserDetailsService userDetailsService(){
        return userDetailsServiceImpl;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationProvider(UserDetailsService userDetailsService,
                                                        PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(daoAuthenticationProvider);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.csrf(csrf->csrf.disable())
                .headers(headers -> headers
                        .frameOptions(frame -> frame
                                .disable())) // Allow to only the paths added
                .authorizeHttpRequests(auth -> auth     //with this the user can Log in or register for free without authentication
                        .requestMatchers("/user/**","/email/**","/categories/**","/Quizz/**","/Question/**").permitAll()
                        .anyRequest().authenticated()
                )
 // [here's a error of version keywords mismatch]               .exceptionHandling((Customizer<ExceptionHandlingConfigurer<HttpSecurity>>) unathorizedHandler)
  //             .httpBasic(Customizer.withDefaults()) //this line is Basic Authentication Line imp in security
        .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
//This class is Security Configuration class