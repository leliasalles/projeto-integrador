package com.dh.hospedagem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@AllArgsConstructor
//@Getter
//@Setter
//@Data
@Entity
@Table
public class Cidade implements Serializable {
    private static final long serialVersionUID = 1L;

//    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//        @GeneratedValue(generator = "increment") // PostgreSQL
//    @GenericGenerator(name = "increment", strategy = "increment") // PostegreSQL
    private Integer id;

    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    @Size(min = 1, max = 50)
    private String pais;

    @JsonIgnore
    @OneToMany(mappedBy = "cidades", fetch = FetchType.EAGER)
    private Set<Product> products = new HashSet<>();

    public Cidade(){}

    public Cidade(String name, String pais, Set<Product> products) {
        this.name = name;
        this.pais = pais;
        this.products = products;
    }

    public Cidade(Integer id, String name, String pais, Set<Product> products) {
        this.id = id;
        this.name = name;
        this.pais = pais;
        this.products = products;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
