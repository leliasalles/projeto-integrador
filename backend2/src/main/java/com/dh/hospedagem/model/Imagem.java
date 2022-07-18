package com.dh.hospedagem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@AllArgsConstructor
//@Getter
//@Setter
@Entity
@Table
public class Imagem implements Serializable {
    private static final long serialVersionUID = 1L;

//    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//        @GeneratedValue(generator = "increment") // PostgreSQL
//        @GenericGenerator(name = "increment", strategy = "increment") // PostegreSQL
   private Integer id;

    @NotNull(message = "campo não pode ser nulo")
    private String titulo;

    @NotNull(message = "campo não pode ser nulo")
    private String url;

    @JsonIgnore
    @ManyToOne
    private Product product;

    public Imagem() {};

    public Imagem(Integer id, Product product, String titulo, String url) {
        this.id = id;
        this.product = product;
        this.titulo = titulo;
        this.url = url;
    }

    public Imagem(Product product, String titulo, String url) {
        this.product = product;
        this.titulo = titulo;
        this.url = url;
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
