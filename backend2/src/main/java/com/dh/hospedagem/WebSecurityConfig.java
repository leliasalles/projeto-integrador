//package com.dh.hospedagem;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.Arrays;
//
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
////    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeHttpRequests().antMatchers("/", "/products").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login")
////                .permitAll().and().logout().permitAll();
////        //http.authorizeHttpRequests().antMatchers("/","/products").permitAll().anyRequest().authenticated().and().httpBasic();
////    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.authorizeHttpRequests().antMatchers("/h2-console/**", "/**", "/products/**","/categorias","/caracteristicas", "/api/usuarios/**").permitAll()
//                .anyRequest().authenticated()
//                .and().csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().headers().frameOptions().sameOrigin();
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("*"));
//        configuration.setAllowedHeaders(Arrays.asList("*"));
//        configuration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder().username("user")
//                .password("senha").roles("ADM")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
//
//}
