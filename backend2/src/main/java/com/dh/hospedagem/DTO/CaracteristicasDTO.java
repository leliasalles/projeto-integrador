package com.dh.hospedagem.DTO;

import com.dh.hospedagem.model.Caracteristic;

public class CaracteristicasDTO {

    private Integer id;
    private String name;
    private String icone;
    private int areaDoQuarto;
    private int numeroCamas;

    public CaracteristicasDTO(){}

    public CaracteristicasDTO(Integer id, String name, String icone,
                              int areaDoQuarto, int numeroCamas){
        this.id = id;
        this.name = name;
        this.icone = icone;
        this.areaDoQuarto =areaDoQuarto;
        this.numeroCamas = numeroCamas;
    }

    public CaracteristicasDTO(Caracteristic caracteristic){
        id = caracteristic.getId();
        name = caracteristic.getName();
        icone = caracteristic.getIcone();
        areaDoQuarto = caracteristic.getAreaDoQuarto();
        numeroCamas = caracteristic.getNumeroCamas();
    }

    public int getId() {
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
}
