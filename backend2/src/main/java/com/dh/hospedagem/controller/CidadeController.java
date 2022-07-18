package com.dh.hospedagem.controller;

import com.dh.hospedagem.DTO.CidadeDTO;
import com.dh.hospedagem.model.Cidade;
import com.dh.hospedagem.repository.CidadeRepository;
import com.dh.hospedagem.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/cidades")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CidadeController {
    //
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeService cidadeService;

    @Transactional(readOnly = true)
    @GetMapping
    public List<CidadeDTO> buscarTodos() {
        List<Cidade> resultado = cidadeRepository.findAll();
        return resultado.stream().map(CidadeDTO::new).collect(Collectors.toList());
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Cidade> atualizar(@PathVariable Integer id, @RequestBody Cidade cidadeDTO){
        cidadeDTO.setId(id);
        cidadeDTO = cidadeRepository.save(cidadeDTO);
        return ResponseEntity.ok(cidadeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCidade(@PathVariable Integer id) {
        if (!cidadeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cidadeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CidadeDTO> adicionar(
            @RequestBody CidadeDTO cidadeDTO) {
        cidadeDTO = cidadeService.salvar(cidadeDTO);
        URI iri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(cidadeDTO.getId()).toUri();

        return ResponseEntity.created(iri).body(cidadeDTO);
    }

}
