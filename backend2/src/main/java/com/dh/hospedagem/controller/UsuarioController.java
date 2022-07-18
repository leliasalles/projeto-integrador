package com.dh.hospedagem.controller;

import com.dh.hospedagem.DTO.UsuarioDTO;
import com.dh.hospedagem.exceptionCustom.VerificaRegraNegocio;
import com.dh.hospedagem.model.Usuario;
import com.dh.hospedagem.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {


    @Autowired
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;

    public UsuarioController(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<Usuario>> listarTodos(){
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    //
    @GetMapping("/{id}")
    public UsuarioDTO buscaUsuario(@PathVariable Integer id){
        Optional<Usuario> usuario = usuarioRepository.findById(id); // retorna um optional, por isso precisa do get
        Usuario entity = usuario.orElseThrow(() -> new VerificaRegraNegocio("Usuário não encontrado"));
        return new UsuarioDTO(entity);
    }

    // salvar usuário
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
    }

    // validando token
    @GetMapping("/validando")
    public ResponseEntity<Boolean> validarLogin(@RequestParam String email,
                                                @RequestParam String senha){
        Optional<Usuario> user = usuarioRepository.findByEmail(email);
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        Usuario usuario = user.get();
        boolean valido = encoder.matches(senha, usuario.getSenha());

        HttpStatus status = (valido) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).body(valido);
    }
}
