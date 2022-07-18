package com.dh.hospedagem.config.security;

import com.dh.hospedagem.config.security.filtros.JwtAutenticar;
import com.dh.hospedagem.config.security.filtros.JwtValidar;
import com.dh.hospedagem.config.security.imppl.DetalhesUsuarioServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class JWTConfig extends WebSecurityConfigurerAdapter {

    private final DetalhesUsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;

    public JWTConfig(DetalhesUsuarioServiceImpl usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeHttpRequests()
//                .antMatchers(HttpMethod.POST, "/login").permitAll()
//                .antMatchers(HttpMethod.GET, "/products").permitAll()
//                .antMatchers(HttpMethod.GET, "/categorias").permitAll()
//                .antMatchers(HttpMethod.POST, "/h2-console/","/h2-console","/h2-console/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/h2-console/","/h2-console","/h2-console/**").permitAll()
//                .anyRequest().authenticated()
//                .and().addFilter(new JwtAutenticar(authenticationManager()))
//                .addFilter((new JwtValidar(authenticationManager())))
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }

        @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests().antMatchers("/h2-console/**", "/**", "/products/**","/categorias","/caracteristicas", "/api/usuarios/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().headers().frameOptions().sameOrigin();
    }


//    @Bean
//    protected CorsConfigurationSource corsConfigurationSource(){
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//
//        return (CorsConfigurationSource) source;
//    }

        @Bean
        CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
