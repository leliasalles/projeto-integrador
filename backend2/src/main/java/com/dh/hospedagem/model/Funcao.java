package com.dh.hospedagem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table
public class Funcao implements Serializable {
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "campo não pode ser nulo!")
    private String nome;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "funcao")
    private Set<Usuario> usuario = new HashSet<>();

    public Funcao(){}

    public Funcao(Integer id, String nome, Set<Usuario> usuario) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
    }

    public Funcao(String nome, Set<Usuario> usuario) {
        this.nome = nome;
        this.usuario = usuario;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(Set<Usuario> usuario) {
        this.usuario = usuario;
    }
}
