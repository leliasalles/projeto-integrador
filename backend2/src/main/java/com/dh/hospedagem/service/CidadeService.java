package com.dh.hospedagem.service;

import com.dh.hospedagem.DTO.CidadeDTO;
import com.dh.hospedagem.exceptionCustom.VerificaRegraNegocio;
import com.dh.hospedagem.model.Cidade;
import com.dh.hospedagem.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;


@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Transactional
    public Cidade salvar(Cidade cidade) {
        boolean existeCidade = cidadeRepository.
                findByName(cidade.getName()).stream().anyMatch(existe -> !existe.equals(cidade));
        System.out.println(existeCidade);

        if (existeCidade) {
            throw new VerificaRegraNegocio("Cidade Já existe!");

        } else {
            return cidadeRepository.save(cidade);
        }

    }

    public void delete(Integer id) {
        try {
            cidadeRepository.deleteById(id);
        } catch (EntityExistsException e) {
            e.getMessage();
        }
    }

    @Transactional
    public CidadeDTO salvar(CidadeDTO cidadeDTO) {
        Cidade entidade = new Cidade();
        entidade.setName(cidadeDTO.getName());
        entidade.setPais(cidadeDTO.getPais());
        entidade = cidadeRepository.save(entidade);
        return new CidadeDTO(entidade);
    }
}
