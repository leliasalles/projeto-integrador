package com.dh.hospedagem.controller;

import com.dh.hospedagem.model.Funcao;
import com.dh.hospedagem.repository.FuncaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/funcao")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FuncaoController {

    @Autowired
    private FuncaoRepository funcaoRepository;

    @GetMapping
    public Iterable<Funcao> mostraFuncoes(){
        Iterable<Funcao> funcaoList = funcaoRepository.findAll();
        return  funcaoList;
    }

    @PostMapping
    public Funcao salvar(@RequestBody Funcao funcao){
        return funcaoRepository.save(funcao);
    }

    @PutMapping
    public Funcao update(@RequestBody Funcao funcao, Integer id){
        return funcaoRepository.save(funcao);
    }
}
