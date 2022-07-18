package com.dh.hospedagem.exceptionCustom;

public class VerificaRegraNegocio extends RuntimeException{
    private static long serialVersionUID = 1L;

    public VerificaRegraNegocio(String mensagem){
        super(mensagem);
    }
}
