package com.springjwt.configuration;

import com.springjwt.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfiguration {

    @Autowired
    private JwtRequestFilter requestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/authenticate", "/sign-up","/soumettre","/project/soumettre","/project/liste","/project/sum/projet","/project/countconge","/project/count/reclamation","/project/count/team","/project/consulter/**"
                        ,"/project/send","/project/reclamation","/project/reclamation/**","/project/traiter/**","project/Tasks/create/**","project/Tasks/TaskUser","project/Tasks/**","project/Tasks","/Tasks/TaskUser","project/**","project/allProjet","project/deletProjet",
                        "project/add","project/Tasks/export/monthly/supervisor/{createdDate}","project/Tasks/export/monthly/{createdDate}","/my-profile","project/affecterprojetAuuser/**","/project/affecterprojetAuuser/**",
                        "/project/my-tasks","/my-profile","project/Taskss/**","/project/**","/project/projects","/project/users").permitAll()
                .requestMatchers("/authenticate").permitAll()
                .requestMatchers("/my-profile").authenticated()
               // .requestMatchers("/project/my-tasks").authenticated()

                .and()
                .authorizeHttpRequests().requestMatchers("/api/**","/project/**")

                .authenticated().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
