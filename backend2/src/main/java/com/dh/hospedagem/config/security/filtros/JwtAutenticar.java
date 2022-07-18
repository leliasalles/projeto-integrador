package com.dh.hospedagem.config.security.filtros;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dh.hospedagem.config.security.data.DetalhesUsuario;
import com.dh.hospedagem.model.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JwtAutenticar extends UsernamePasswordAuthenticationFilter {
    public static final int TOKEN_EXP = 1_000_000;

    // só para desenvolvimento
    public static final String TOKEN = "password";

    private final AuthenticationManager authenticationManager;


    public JwtAutenticar(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Usuario usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    usuario.getEmail(),
                    usuario.getSenha(),
                    new ArrayList<>() // lista com permissões do usuario
            ));
        } catch (IOException e) {
            throw  new RuntimeException("Falha ao autenticar usuário!");
        }


    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        DetalhesUsuario detalhesUsuario = (DetalhesUsuario) authResult.getPrincipal();
        String token = JWT.create()
                .withSubject(detalhesUsuario.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() * TOKEN_EXP))
                .sign(Algorithm.HMAC256(TOKEN));

        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
