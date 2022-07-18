package com.dh.hospedagem.DTO;

import com.dh.hospedagem.model.Product;
import com.dh.hospedagem.model.Reserva;
import com.dh.hospedagem.model.Usuario;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaDTO {

    private Integer id;
    private LocalTime horario;
    private  LocalDate dataInicialDaReserva;
    private LocalDate dataFinalDaReserva;
    private Product product;
    private Usuario usuario;

    public  ReservaDTO() {}

    public ReservaDTO(Reserva reserva) {
        id = reserva.getId();
        horario = reserva.getHorario();
        dataInicialDaReserva = reserva.getDataInicialReserva();
        dataFinalDaReserva = reserva.getDataFinalReserva();
        product = reserva.getProduct();
        usuario = reserva.getUsuario();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public LocalDate getDataInicialDaReserva() {
        return dataInicialDaReserva;
    }

    public void setDataInicialDaReserva(LocalDate dataInicialDaReserva) {
        this.dataInicialDaReserva = dataInicialDaReserva;
    }

    public LocalDate getDataFinalDaReserva() {
        return dataFinalDaReserva;
    }

    public void setDataFinalDaReserva(LocalDate dataFinalDaReserva) {
        this.dataFinalDaReserva = dataFinalDaReserva;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

