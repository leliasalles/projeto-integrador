package com.dh.hospedagem.exceptionCustom;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// personalização das exceções
@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionCustom extends ResponseEntityExceptionHandler {

    MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Error error= new Error();

        List<Error.Campo> campos = new ArrayList<>();

        for (ObjectError erro: ex.getBindingResult().getAllErrors()) {
            String nomeErro = ((FieldError) erro).getField();
            String errorReporter = messageSource.getMessage( erro, LocaleContextHolder.getLocale());

            campos.add(new Error.Campo(nomeErro, errorReporter));
        }

        error.setStatus(status.value());
        error.setNome("Campos inválidos!");
        error.setDataHorario(LocalDateTime.now());
        error.setCampos(campos);

        return handleExceptionInternal(ex, error,
                headers,status, request);
    }

    @ExceptionHandler(VerificaRegraNegocio.class)
    public ResponseEntity<Object> trataErroRegraNegocio(VerificaRegraNegocio ex, WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Error er = new Error();
        er.setStatus(status.value());
        er.setDataHorario(LocalDateTime.now());
        er.setNome(ex.getMessage());

        return handleExceptionInternal(ex, er, new HttpHeaders(), status, request);
    }
}
