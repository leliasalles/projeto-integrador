package com.dh.hospedagem.controller;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ErroController implements ErrorController {
        private final static String PATH = "/error";

        // informar que a rota não está disponível
        @RequestMapping(PATH)
        @ResponseBody
        public String getErrorPath() {
            return "Rota de Api não encontrada ou requisição com erros!";
        }

}
