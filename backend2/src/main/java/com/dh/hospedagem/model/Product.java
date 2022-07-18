package com.dh.hospedagem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@Data
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//        @GeneratedValue(generator = "increment") // PostgreSQL
//    @GenericGenerator(name = "increment", strategy = "increment") // PostegreSQL
//    @Column(name = "id_product")
    private Integer id;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @Size(min = 5, max = 100)
    private String description;

//    muitos produtos para uma categoria
    @ManyToOne
    private Category categorias;

    //muitos imoveis podem estar em uma cidade
    @ManyToOne
    private Cidade cidades;

    @OneToMany(  fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Set<Imagem> imagem = new HashSet<>();

    @ManyToMany
    @JoinTable(
                name = "productcaracteristic",
                joinColumns = @JoinColumn(name = "id_product"),
                inverseJoinColumns = @JoinColumn(name = "id_caracteristic")
    )
    private Set<Caracteristic> caracteristic = new HashSet<>(); /** **/

//    @JsonIgnore
    @JsonBackReference("product")
    @OneToMany(mappedBy = "product")
    private Set<Reserva> reserva = new HashSet<>();

    public Product(){}

    public Product(Integer id, String name, String description, Category categorias, Cidade cidades, Set<Imagem> imagem, Set<Caracteristic> caracteristic, Set<Reserva> reserva) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categorias = categorias;
        this.cidades = cidades;
        this.imagem = imagem;
        this.caracteristic = caracteristic;
        this.reserva = reserva;
    }

    public Product(String name, String description, Category categorias, Cidade cidades, Set<Imagem> imagem, Set<Caracteristic> caracteristic, Set<Reserva> reserva) {
        this.name = name;
        this.description = description;
        this.categorias = categorias;
        this.cidades = cidades;
        this.imagem = imagem;
        this.caracteristic = caracteristic;
        this.reserva = reserva;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategorias() {
        return categorias;
    }

    public void setCategorias(Category categorias) {
        this.categorias = categorias;
    }

    public Cidade getCidades() {
        return cidades;
    }

    public void setCidades(Cidade cidades) {
        this.cidades = cidades;
    }

    public Set<Imagem> getImagem() {
        return imagem;
    }

    public void setImagem(Set<Imagem> imagem) {
        this.imagem = imagem;
    }

    public Set<Caracteristic> getCaracteristic() {
        return caracteristic;
    }

    public void setCaracteristic(Set<Caracteristic> caracteristic) {
        this.caracteristic = caracteristic;
    }

    public Set<Reserva> getReserva() {
        return reserva;
    }

    public void setReserva(Set<Reserva> reserva) {
        this.reserva = reserva;
    }
}
