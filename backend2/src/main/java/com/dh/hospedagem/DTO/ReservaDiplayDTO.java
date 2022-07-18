package com.dh.hospedagem.DTO;

import com.dh.hospedagem.model.Reserva;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaDiplayDTO {

    private Integer id;
    private LocalTime horario;
    private LocalDate dataInicialDaReserva;
    private LocalDate dataFinalDaReserva;

    public ReservaDiplayDTO(Reserva reserva) {
        id = reserva.getId();
        horario = reserva.getHorario();
        dataInicialDaReserva = reserva.getDataInicialReserva();
        dataFinalDaReserva = reserva.getDataFinalReserva();
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
}
