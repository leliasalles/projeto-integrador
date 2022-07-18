package com.dh.hospedagem.DTO;

import com.dh.hospedagem.model.Imagem;
import com.dh.hospedagem.model.Product;

import javax.validation.constraints.NotNull;

public class ImagemDTO {

    private Integer id;
    private String titulo;
    private @NotNull(message = "campo não pode ser nulo")
    String url;
    private Product product;

    public ImagemDTO(Imagem imagem){
        id = imagem.getId();
        product = imagem.getProduct();
        titulo =imagem.getTitulo();
        url = imagem.getUrl();

    }

    public ImagemDTO(Product product, String titulo, String url) {
        this.product = product;
        this.titulo = titulo;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
