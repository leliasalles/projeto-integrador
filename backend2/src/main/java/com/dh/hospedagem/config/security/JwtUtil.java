package com.dh.hospedagem.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.ReactiveTransaction;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    private String KEY = "password";

    public String extrairUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(KEY)
                .parseClaimsJwt(token)
                .getBody();
    }

    public Date expiracao(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean tokenValido(String token){
        return expiracao(token).before(new Date());
    }

    public String geraToken(UserDetails userDetails){
        Map<String, Object> cria = new HashMap<>();
        return criaToken(cria, userDetails.getUsername());
    }

    private String criaToken(Map<String, Object> cria, String username) {
        return Jwts.builder()
                .setClaims(cria)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60))
                .signWith(SignatureAlgorithm.ES256, KEY).compact();
    }


    public Boolean validar(String token, UserDetails userDetails){
        final String username = extrairUserName(token);
        return (username.equals(userDetails.getUsername())
        && !tokenValido(token));
    }
}
