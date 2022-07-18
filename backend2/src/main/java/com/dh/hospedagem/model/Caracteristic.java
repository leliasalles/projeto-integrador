package com.dh.hospedagem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Caracteristic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 5, max = 50)
    private String name;

    @NotNull
    @Size(min = 1, max = 100)
    private String icone;

    @NotNull
    @Min(0)
    private int areaDoQuarto;

    @NotNull
    @Min(0)
    private int numeroCamas;


    @JsonIgnore
    @ManyToMany(mappedBy = "caracteristic", fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>(); // *

    public Caracteristic() {}

    public Caracteristic(String name, String icone, int areaDoQuarto, int numeroCamas, Set<Product> products) {
        this.name = name;
        this.icone = icone;
        this.areaDoQuarto = areaDoQuarto;
        this.numeroCamas = numeroCamas;
        this.products = products;
    }

    public Caracteristic(Integer id, String name, String icone, int areaDoQuarto, int numeroCamas, Set<Product> products) {
        this.id = id;
        this.name = name;
        this.icone = icone;
        this.areaDoQuarto = areaDoQuarto;
        this.numeroCamas = numeroCamas;
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

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public int getAreaDoQuarto() {
        return areaDoQuarto;
    }

    public void setAreaDoQuarto(int areaDoQuarto) {
        this.areaDoQuarto = areaDoQuarto;
    }

    public int getNumeroCamas() {
        return numeroCamas;
    }

    public void setNumeroCamas(int numeroCamas) {
        this.numeroCamas = numeroCamas;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
