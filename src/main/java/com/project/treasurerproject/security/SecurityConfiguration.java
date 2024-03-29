package com.project.treasurerproject.security;

import com.project.treasurerproject.constant.ERole;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final AuthTokenFilter authTokenFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    private static final String[] WHITE_LIST_URL = {
            "/api/auth/**",
            "/login"
    };
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                                .requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers(HttpMethod.GET,"/api/periods").hasAnyAuthority(ERole.ROLE_ADMIN.name(),ERole.ROLE_MEMBER.name())
                                .requestMatchers(HttpMethod.GET,"/api/periods/**").hasAnyAuthority(ERole.ROLE_ADMIN.name(),ERole.ROLE_MEMBER.name())
                                .requestMatchers(HttpMethod.GET,"/api/tracking").hasAnyAuthority(ERole.ROLE_ADMIN.name(),ERole.ROLE_MEMBER.name())
                                .requestMatchers(HttpMethod.GET,"/api/tracking/**").hasAnyAuthority(ERole.ROLE_ADMIN.name(),ERole.ROLE_MEMBER.name())
                                .requestMatchers(HttpMethod.GET,"/api/accumulations").hasAnyAuthority(ERole.ROLE_ADMIN.name(),ERole.ROLE_MEMBER.name())
                                .requestMatchers(HttpMethod.GET,"/api/accumulations/**").hasAnyAuthority(ERole.ROLE_ADMIN.name(),ERole.ROLE_MEMBER.name())
                                .requestMatchers(HttpMethod.GET,"/api/payments").hasAnyAuthority(ERole.ROLE_ADMIN.name(),ERole.ROLE_MEMBER.name())
                                .requestMatchers(HttpMethod.GET,"/api/payments/**").hasAnyAuthority(ERole.ROLE_ADMIN.name(),ERole.ROLE_MEMBER.name())
                                .requestMatchers("/api/members").hasAnyAuthority(ERole.ROLE_ADMIN.name(),ERole.ROLE_MEMBER.name())
                                .requestMatchers("/api/members/**").hasAnyAuthority(ERole.ROLE_ADMIN.name(),ERole.ROLE_MEMBER.name())
                                .requestMatchers("/api/periods/**").hasAuthority(ERole.ROLE_ADMIN.name())
                                .requestMatchers("/api/tracking/**").hasAuthority(ERole.ROLE_ADMIN.name())
                                .requestMatchers("/api/accumulations/**").hasAuthority(ERole.ROLE_ADMIN.name())
                                .requestMatchers("/api/payments/**").hasAuthority(ERole.ROLE_ADMIN.name())
                                .anyRequest()
                                .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
