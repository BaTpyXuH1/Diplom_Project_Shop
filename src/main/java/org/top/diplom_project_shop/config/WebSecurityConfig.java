package org.top.diplom_project_shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.top.diplom_project_shop.security.DbUserDetailsService;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
                "/css/*.css", "/images/*");
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests

                                .requestMatchers("/client/*","/orderProduct/**","/order/**","/orderProduct/**","/order/update/","/product/*").hasRole("ADMIN")
                                .requestMatchers("/client/*","/orderProduct/**","/order/**","/orderProduct/**","/order/update/","/product/*").hasRole("USER")
                                .requestMatchers("/product/*","/registration", "/service/generateBase", "/client/add").anonymous()
                                .requestMatchers( "/client/add", "/logout", "/product/add","/order/add","/client/*","/client/detail","/product/*").authenticated()
                                .requestMatchers( "/","/webjars/**","/order/update/","/orderProduct/add/","/catalog","/product/*","/client/*").permitAll()

                                .requestMatchers("/admin/*","/orderProduct/**").hasRole("ADMIN")
                                .requestMatchers("/product","/registration", "/service/generateBase", "/client/add").anonymous()
                                .requestMatchers( "/client/add", "/logout", "/product/add","/basket/*","/orderProduct/add/").authenticated()
                                .requestMatchers( "/","/webjars/**","client/add").permitAll()



                            .requestMatchers("/client/*","/client/delete/**","/order/delete/**"
                            ).hasRole("ADMIN")
                            .requestMatchers("/client/*","/client/update/**","/order/update/**",
                                    "/product/update/**","/orderProduct/update/**","/orderProduct/orderProduct-detail").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                        .failureUrl("/login?error=true")
                        .defaultSuccessUrl("/")

                )

                .logout().logoutSuccessUrl("/login");

        return http.build();
    }


    // Зависимость кодировщика паролей
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(); // стандартный кодировщик Spring
    }

    // КОНФИГУРАЦИЯ ДЛЯ ПОДКЛЮЧЕНИЯ БД
    @Bean
    public UserDetailsService userDetailsService() {
        return new DbUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsManager users(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService())
                .passwordEncoder(encoder())
                .and()
                .authenticationProvider(authenticationProvider())
                .build();
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setAuthenticationManager(authenticationManager);
        return jdbcUserDetailsManager;
    }
}

