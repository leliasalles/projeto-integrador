package com.dh.hospedagem.config.security;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeResource {
    @GetMapping("/")
    public String auth(){
        return ("<h1>Bem-vindo!</h1>");
    }

    @GetMapping("/user")
    public String user(){
        return ("<h1>Bem-vindo usuario!</h1>");
    }

    @GetMapping("/admin")
        public String admin(){
            return ("<h1>Bem-Vindo Admin</h1>");
        }
}


