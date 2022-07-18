package com.dh.hospedagem.DTO;

import com.dh.hospedagem.model.Funcao;
import com.dh.hospedagem.model.Usuario;

public class UsuarioDTO {

    private Integer id;
    private String name;
    private String lastname;
    private String email;
    private String senha;
    private Funcao funcao;

//    private Funcao funcao;

    public UsuarioDTO(Usuario usuario){
        id = usuario.getId();
        name = usuario.getName();
        lastname = usuario.getLastname();
        funcao = usuario.getFuncao();
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

//    public String getEmail() {
//        return email;
//    }

//    public void setEmail(String email) {
//        this.email = email;
//    }

//    public String getSenha() {
//        return senha;
//    }
//
//    public void setSenha(String senha) {
//        this.senha = senha;
//    }

}
