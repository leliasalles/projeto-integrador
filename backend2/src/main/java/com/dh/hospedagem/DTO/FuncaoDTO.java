package com.dh.hospedagem.DTO;

public class FuncaoDTO {
    private Integer id;
    private String nome;

    public FuncaoDTO(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public FuncaoDTO(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getNome() {
        return nome;
    }

//    public void setNome(String nome) {
//        this.nome = nome;
//    }
}
