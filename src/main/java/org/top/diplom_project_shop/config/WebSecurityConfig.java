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
                    "/css/*.css","/images/*");
        }
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests((requests) -> requests
                            .requestMatchers("/", "/webjars/**").permitAll()
                                    .requestMatchers("/user/*").hasRole("USER")
                                    .requestMatchers("/admin/*").hasRole("ADMIN")
                                    .requestMatchers( "/registration","/service/generateBase","/client/add").anonymous()
                                    .requestMatchers("/catalog","/logout").authenticated()

//                            .requestMatchers("/user/*","/user/delete/**","/order/delete/**"
//                            ).hasRole("ADMIN")
//                            .requestMatchers("/user/*","/user/update/**","/order/update/**",
//                                    "/item/update/**","/orderItem/update/**").hasRole("ADMIN")
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

        // ?????????????????????? ?????????????????????? ??????????????
        @Bean
        public PasswordEncoder encoder() {
            return new BCryptPasswordEncoder(); // ?????????????????????? ???????????????????? Spring
        }

        // ???????????????????????? ?????? ?????????????????????? ????
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

