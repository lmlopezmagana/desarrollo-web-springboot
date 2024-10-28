package com.openwebinars.webdata.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
                .username("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/producto/**").authenticated()
                        .anyRequest().permitAll()
                )
                .requestCache(cache -> {
                    HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
                    requestCache.setMatchingRequestParameterName(null);
                    cache.requestCache(requestCache);
                })
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/producto/list", true)
                        .permitAll()
                );

        // Añadimos esto para poder acceder a la consola de H2
        // con Spring Security habilitado.
        http.csrf((csrf) -> {
            csrf.ignoringRequestMatchers("/h2/**");
        });
        http.headers((headers) ->
                headers.frameOptions((opts) -> opts.disable()));
        return http.build();
    }

}
