package org.japb.spacecraftservice.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

//@Configuration
//@EnableWebSecurity
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .formLogin(withDefaults());
        // @formatter:on
        return http.build();
    }

    // @formatter:off
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("Jaime")
                .password("2030201811*")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
/*
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserDetails adminUser = User.builder()
                .username("shail@mail.com")
                .password(passwordEncoder.encode("shail@123"))
                .authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))
                .build();

        UserDetails userUser = User.builder()
                .username("john@mail.com")
                .password(passwordEncoder.encode("john@123"))
                .authorities(new SimpleGrantedAuthority("ROLE_USER"))
                .build();

        return new InMemoryUserDetailsManager(Arrays.asList(adminUser, userUser));
    }
*/
/*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeRequests()
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .requestMatchers("/swagger-ui.html", "/v3/api-docs/**","/swagger-ui/**").permitAll()
                .requestMatchers("/api/spacecrafts/**").authenticated()
                .anyRequest()
                .authenticated()
                .and()
                .build();
    }
*/


/*
        @Bean
        protected void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http.authorizeHttpRequests(auth -> auth.requestMatchers()
                    .permitAll()
                    .requestMatchers("/swagger-ui.html", "/v3/api-docs/", "/swagger-ui/").permitAll()
                    .anyRequest().authenticated())
                    .build();
        }

        // @formatter:off
    /*
        @Bean
        public InMemoryUserDetailsManager userDetailsService() {
            UserDetails user = User.withDefaultPasswordEncoder()
                    .username("user")
                    .password("password")
                    .roles("USER")
                    .build();
            return new InMemoryUserDetailsManager(user);
        }

     */
        // @formatter:on

    }



