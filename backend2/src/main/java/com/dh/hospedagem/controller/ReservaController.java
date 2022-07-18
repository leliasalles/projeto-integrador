package com.dh.hospedagem.controller;

import com.dh.hospedagem.DTO.ReservaDTO;
import com.dh.hospedagem.DTO.ReservaDiplayDTO;
import com.dh.hospedagem.exceptionCustom.VerificaRegraNegocio;
import com.dh.hospedagem.model.Reserva;
import com.dh.hospedagem.repository.ReservaCustomRepository;
import com.dh.hospedagem.repository.ReservaRepository;
import com.dh.hospedagem.service.ReservaService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/reservas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReservaController {

    private final ReservaCustomRepository reservaCustomRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ReservaService reservaService;

    public ReservaController(ReservaCustomRepository reservaCustomRepository) {
        this.reservaCustomRepository = reservaCustomRepository;
    }

    @GetMapping
    public List<ReservaDiplayDTO> mostraTodasReservas(){
        List<Reserva> reservas = reservaRepository.findAll();
        return reservas.stream().map(ReservaDiplayDTO::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/p/{id}")
    public @ResponseBody List<ReservaDTO> mostraProdutosPorId(@PathVariable Integer id) throws ObjectNotFoundException {
        try {
            List<Reserva> reservaDTOS = reservaRepository.findByReservasPorIdDoProduto(id);
            return reservaDTOS.stream().map(ReservaDTO::new).collect(Collectors.toList());
        } catch (ObjectNotFoundException e){
            throw new VerificaRegraNegocio("Produto não encontrado!");
        }

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ReservaDTO> adicionar(
            @RequestBody ReservaDTO reservaDTO) {
        reservaDTO = reservaService.salvar(reservaDTO);
        URI iri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(reservaDTO.getId()).toUri();

        return (ResponseEntity<ReservaDTO>) ResponseEntity.created(iri).body(reservaDTO);
    }
    
    @DeleteMapping(value = "/{id}")
    public void deletar(@PathVariable Integer id){
        reservaRepository.deleteById(id);
    }


    @GetMapping("/pesquisar")
    public List<Reserva> pesquisaPorData(@RequestParam(value = "dataInicialReserva", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicialReserva,
                                         @RequestParam(value = "dataFinalReserva", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinalReserva){
        return reservaCustomRepository.pesquisar(dataInicialReserva, dataFinalReserva);
    }

    // http://localhost:8080/reservas/pesquisa2?dataInicialReserva=2022-07-02&dataFinalReserva=2022-07-10
    @GetMapping("/pesquisa2")
    public List<Reserva> pesquisarDATAS(@RequestParam(value = "dataInicialReserva", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicialReserva,
                                        @RequestParam(value = "dataFinalReserva", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinalReserva){
        return reservaRepository.obterReservasData(dataInicialReserva, dataFinalReserva);
    }

    //http://localhost:8080/reservas/produtoid?id=20
    @GetMapping("/produtoid")
    public List<Reserva> pesquisaProdutoId(@RequestParam(value = "id") Integer id){
    return reservaRepository.obterReservasPorIdProduto(id);
    }


    //http://localhost:8080/reservas/reserva_usuario?id=20
    @GetMapping("/reserva_usuario")
    public List<Reserva> pesquisaUsuarioId(@RequestParam(value = "id") Integer id){
        return reservaRepository.obterReservaPorIdUsuario(id);
    }

}
