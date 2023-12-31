//package com.example.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
//import org.springframework.security.web.SecurityFilterChain;
//
//import java.util.Collection;
//import java.util.Map;
//
//import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .authorizeHttpRequests(registry -> registry
//                        .requestMatchers(antMatcher("/client/**")).hasRole("admin")
//                        .anyRequest().authenticated()
//                )
//                .oauth2ResourceServer(oauth2Configurer -> oauth2Configurer.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwt -> {
//                    Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");
//                    Collection<String> roles = realmAccess.get("roles");
//                    var grantedAuthorities = roles.stream()
//                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//                            .toList();
//                    return new JwtAuthenticationToken(jwt, grantedAuthorities);
//                })))
//        ;
//
//        return httpSecurity.build();
//    }
//}



