package com.TP1.ProductAPI.Security;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // CSRF desactive (API stateless + console H2 de dev)
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Swagger / OpenAPI
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // Console H2 (chemin resolu automatiquement : /h2-console/**)
                        .requestMatchers(toH2Console()).permitAll()

                        // Interface web Thymeleaf
                        .requestMatchers("/products/**").permitAll()

                        // Lecture des produits : ouverte a tous
                        .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()

                        // Creation d'un produit : reservee au role ADMIN
                        .requestMatchers(HttpMethod.POST, "/api/products").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                // Autorise l'affichage de la console H2 dans une iframe de meme origine
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
