package com.dh.hospedagem.DTO;

import com.dh.hospedagem.model.Cidade;

import java.util.List;
import java.util.stream.Collectors;

public class CidadeDTO {

    private Integer id;
    private String name;
    private String pais;
//    private Set<Product> products = new HashSet<>();

    public CidadeDTO(){}

    public CidadeDTO(Integer id, String name, String pais){
        this.id = id;
        this.name = name;
        this.pais = pais;
    }

    public CidadeDTO(Cidade entidade) {
        id = entidade.getId();
        name =entidade.getName();
        pais = entidade.getPais();
    }

    public static List<CidadeDTO> converter(List<Cidade> cidades){
        return cidades.stream().map(CidadeDTO::new).collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public String getPais() {
        return pais;
    }
//
//    public void setPais(String pais) {
//        this.pais = pais;
//    }
}
