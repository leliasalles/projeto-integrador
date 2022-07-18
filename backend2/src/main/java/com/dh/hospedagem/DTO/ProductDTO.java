package com.dh.hospedagem.DTO;

import com.dh.hospedagem.model.Category;
import com.dh.hospedagem.model.Cidade;
import com.dh.hospedagem.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDTO {
    private Integer id;
    private String name;
    private String description;
    private Category categorias;
    private Cidade cidades;
    private List<ImagemDTO> imagem = new ArrayList<ImagemDTO>();
    private List<CaracteristicasDTO> caracteristicas = new ArrayList<CaracteristicasDTO>();// observar

    public ProductDTO(){}

    public ProductDTO(Integer id, String name,
                      String description, Category category,
                      Cidade cidade, List<ImagemDTO> imagems, List<CaracteristicasDTO> caracteristics){
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.categorias = category;
        this.cidades = cidade;
        this.imagem = imagems;
        this.caracteristicas = caracteristics;
    }

    // faz o carregamento das listas dos produtos
    public ProductDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        categorias = entity.getCategorias();
        cidades = entity.getCidades();
        imagem = entity.getImagem().stream().map(x -> new ImagemDTO(x)).collect(Collectors.toList());
        caracteristicas = entity.getCaracteristic().stream().map(y -> new CaracteristicasDTO(y)).collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategorias() {
        return categorias;
    }

//    public void setCategorias(Category categorias) {
//        this.categorias = categorias;
//    }

    public Cidade getCidades() {
        return cidades;
    }

//    public void setCidades(Cidade cidades) {
//        this.cidades = cidades;
//    }

    public List<ImagemDTO> getImagem() {
        return imagem;
    }

//    public void setImagem(List<ImagemDTO> imagem) {
//        this.imagem = imagem;
//    }

    public List<CaracteristicasDTO> getCaracteristicas() {
        return caracteristicas;
    }

//    public void setCaracteristicas(List<CaracteristicasDTO> caracteristicas) {
//        this.caracteristicas = caracteristicas;
//    }
}
