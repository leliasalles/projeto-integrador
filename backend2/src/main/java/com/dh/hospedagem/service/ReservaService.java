package com.dh.hospedagem.service;

import com.dh.hospedagem.DTO.ReservaDTO;
import com.dh.hospedagem.model.Reserva;
import com.dh.hospedagem.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public ReservaDTO salvar(ReservaDTO reservaDTO){
        Reserva reserva = new Reserva();

        reserva.setDataInicialReserva(reservaDTO.getDataInicialDaReserva());
        reserva.setDataFinalReserva(reservaDTO.getDataFinalDaReserva());
        reserva.setHorario(reservaDTO.getHorario());
        reserva.setProduct(reservaDTO.getProduct());
        reserva.setUsuario(reservaDTO.getUsuario());

        reserva = reservaRepository.save(reserva);
        return new ReservaDTO(reserva);

    }
}
