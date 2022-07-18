package com.dh.hospedagem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
//@Setter
//@Getter
//@AllArgsConstructor
@Table(name = "categoria")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Mysql - gera o auto incremento
//    @GeneratedValue(generator = "increment") // PostgreSQL
//    @GenericGenerator(name = "increment", strategy = "increment") // PostegreSQL
    @Column (name="id_category")
    private Integer id;

    @NotNull
    @Size(min=2, max=100)
    private String name;

    @Min(1) // maximo 5 estrelas
    @Max(5)
    private int classification;

    @NotNull
    @Size(min=10, max = 300)
    private String description;

    @NotNull
    @Size(min= 5, max = 100)
    private String url_imagem;

    // uma categoria pode ter 0 ou muitos produtos
    @OneToMany(mappedBy = "categorias", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Product> produtos;

    public Category(){}

    public Category(String name, int classification, String description, String url_imagem, List<Product> produtos) {
        this.name = name;
        this.classification = classification;
        this.description = description;
        this.url_imagem = url_imagem;
        this.produtos = produtos;
    }

    public Category(Integer id, String name, int classification, String description, String url_imagem, List<Product> produtos) {
        this.id = id;
        this.name = name;
        this.classification = classification;
        this.description = description;
        this.url_imagem = url_imagem;
        this.produtos = produtos;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public int getClassification() {
        return classification;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl_imagem() {
        return url_imagem;
    }

    public void setUrl_imagem(String url_imagem) {
        this.url_imagem = url_imagem;
    }

    public List<Product> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Product> produtos) {
        this.produtos = produtos;
    }
}
