package com.dh.hospedagem.service;

import com.dh.hospedagem.DTO.ImagemDTO;
import com.dh.hospedagem.model.Imagem;
import com.dh.hospedagem.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    private ImagemRepository imagemRepository;

    public ImagemDTO salvar(ImagemDTO imagemDTO){
        Imagem imagem = new Imagem();
        imagem.setId(imagemDTO.getId());
        imagem.setTitulo(imagemDTO.getTitulo());
        imagem.setUrl(imagemDTO.getUrl());
        imagem.setProduct(imagemDTO.getProduct());

        imagem = imagemRepository.save(imagem);
        return new ImagemDTO(imagem);
    }
}
