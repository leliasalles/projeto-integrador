package com.dh.hospedagem.DTO;

import com.dh.hospedagem.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


public class CategoryDTO {

    private Integer id;
    private String name;
    private int classification;
    private String description;
    private String url_imagem;
//    private List<Product> produtos;

    public CategoryDTO(){}

    public CategoryDTO(Integer id, String name, int classification, String description, String url_imagem) {
        this.id = id;
        this.name = name;
        this.classification = classification;
        this.description = description;
        this.url_imagem = url_imagem;
    }

    public CategoryDTO(String name, int classification, String description, String url_imagem) {
        this.name = name;
        this.classification = classification;
        this.description = description;
        this.url_imagem = url_imagem;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getClassification() {
//        return classification;
//    }
//
//    public void setClassification(int classification) {
//        this.classification = classification;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getUrl_imagem() {
//        return url_imagem;
//    }
//
//    public void setUrl_imagem(String url_imagem) {
//        this.url_imagem = url_imagem;
//    }
}
