package tpo.uade.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tpo.uade.api.model.RoleEnum;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthFilter;
        private final AuthenticationProvider authenticationProvider;


        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http
                        .csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(req ->
                                req.requestMatchers("/auth/**").permitAll()
                                .requestMatchers("/user/**").hasAnyAuthority(RoleEnum.ADMIN.name()).anyRequest().authenticated())
                        .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                        .authenticationProvider(authenticationProvider)
                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                        .build();
        }

        /*
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http
                        .csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(req -> req.requestMatchers("**").permitAll())
                        .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                        .authenticationProvider(authenticationProvider)
                        //.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                        .build();
        }

         */

}
