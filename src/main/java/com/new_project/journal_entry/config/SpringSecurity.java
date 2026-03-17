// package com.new_project.journal_entry.config;

// import net.engineeringdigest.journalApp.service.UserDetailsServiceImpl;
// import org.springframework.beans.factory.annotation.Autowired;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.Customizer;
// import
// org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import
// org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SpringSecurity {

// @Autowired
// private UserDetailsServiceImpl userDetailsService;

// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
// Exception {

// return http.authorizeHttpRequests(request -> request
// .requestMatchers("/public/**").permitAll() // Tells spring that any request
// preceeding with "/public"
// // shall be permitter without any authentication
// .requestMatchers("/journal/**", "/user/**").authenticated()
// .requestMatchers("/admin/**").hasRole("ADMIN") // Tells spring that any
// rerquest preceeding with
// // "/admin" will have ADMIN permissions
// .anyRequest().authenticated()) // Tells spring to authenticate the rest of
// the requests, whatever it may
// // be.....
// .httpBasic(Customizer.withDefaults())
// .csrf(AbstractHttpConfigurer::disable)
// .build();
// }

// @Autowired
// public void configureGlobal(AuthenticationManagerBuilder auth) throws
// Exception {
// auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
// }

// @Bean
// public PasswordEncoder passwordEncoder() {
// return new BCryptPasswordEncoder();
// }
// }
