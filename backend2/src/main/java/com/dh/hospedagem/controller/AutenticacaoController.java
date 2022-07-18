package com.dh.hospedagem.controller;

import com.dh.hospedagem.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.spi.LoginModule;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AutenticacaoController {

//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @PostMapping
//    public ResponseEntity<?> autenticacao(@RequestBody @Valid Login login){
////        System.out.println(login.getEmail());
////        System.out.println(login.getSenha());
//
////        authenticationManager.authenticate(dados);
//
//        return ResponseEntity.ok().build();
//    }

}
