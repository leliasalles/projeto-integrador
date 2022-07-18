package com.dh.hospedagem.controller;

import com.dh.hospedagem.DTO.ImagemDTO;
import com.dh.hospedagem.DTO.ReservaDTO;
import com.dh.hospedagem.model.Imagem;
import com.dh.hospedagem.model.Product;
import com.dh.hospedagem.repository.ImagemRepository;
import com.dh.hospedagem.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/imagem")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImagemController {

    @Autowired
    private ImagemRepository imagemRepository;

    @Autowired
    private ImageService imageService;

    @GetMapping
    public List<ImagemDTO> listar(){
        List<Imagem> imagems = imagemRepository.findAll();
        List<ImagemDTO> list = new ArrayList<>();
        for (Imagem t : imagems) {
            ImagemDTO imagemDTO = new ImagemDTO(t);
            list.add(imagemDTO);
        }
        return list;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ImagemDTO> adicionar(
            @RequestBody ImagemDTO imagemDTO) {
        imagemDTO = imageService.salvar(imagemDTO);
        URI iri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(imagemDTO.getId()).toUri();

        return (ResponseEntity<ImagemDTO>) ResponseEntity.created(iri).body(imagemDTO);
    }
}
