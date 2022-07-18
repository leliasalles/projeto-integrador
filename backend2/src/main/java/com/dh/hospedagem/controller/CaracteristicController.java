package com.dh.hospedagem.controller;

import com.dh.hospedagem.DTO.CaracteristicasDTO;
import com.dh.hospedagem.model.Caracteristic;
import com.dh.hospedagem.repository.CaracteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/caracteristicas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CaracteristicController {

    @Autowired
    private CaracteristicRepository caracteristicRepository;

    @GetMapping
    public List<CaracteristicasDTO> listar(){
        List<Caracteristic> caracteristics = caracteristicRepository.findAll();
        return caracteristics.stream().map(CaracteristicasDTO::new).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Caracteristic adicionar(@Valid @RequestBody Caracteristic caracteristic){
        return caracteristicRepository.save(caracteristic);
    }
}
