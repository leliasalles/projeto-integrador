package com.dh.hospedagem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import net.bytebuddy.utility.nullability.MaybeNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

//@AllArgsConstructor
//@NoArgsConstructor
//@Setter
//@Getter
//@Data
@Entity
@Table
public class Reserva implements Serializable {
    private static final long serialVersionUID = 1L;

//    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @NotNull
    private LocalTime horario;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInicialReserva;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFinalReserva;

    @ManyToOne
    private Product product;

    @JsonIgnore
    @ManyToOne
//    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Reserva(){}

    public Reserva(LocalTime horario, LocalDate dataInicialReserva, LocalDate dataFinalReserva, Product product, Usuario usuario) {
        this.horario = horario;
        this.dataInicialReserva = dataInicialReserva;
        this.dataFinalReserva = dataFinalReserva;
        this.product = product;
        this.usuario = usuario;
    }

    public Reserva(Integer id, LocalTime horario, LocalDate dataInicialReserva, LocalDate dataFinalReserva, Product product, Usuario usuario) {
        this.id = id;
        this.horario = horario;
        this.dataInicialReserva = dataInicialReserva;
        this.dataFinalReserva = dataFinalReserva;
        this.product = product;
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

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public LocalDate getDataInicialReserva() {
        return dataInicialReserva;
    }

    public void setDataInicialReserva(LocalDate dataInicialReserva) {
        this.dataInicialReserva = dataInicialReserva;
    }

    public LocalDate getDataFinalReserva() {
        return dataFinalReserva;
    }

    public void setDataFinalReserva(LocalDate dataFinalReserva) {
        this.dataFinalReserva = dataFinalReserva;
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
