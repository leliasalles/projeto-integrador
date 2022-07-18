package com.dh.hospedagem.config.security.imppl;

import com.dh.hospedagem.config.security.data.DetalhesUsuario;
import com.dh.hospedagem.model.Usuario;
import com.dh.hospedagem.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetalhesUsuarioServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public DetalhesUsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
        if(usuario.isEmpty()){
            throw new UsernameNotFoundException("Email não cadastrado!");
        }

        return new DetalhesUsuario(usuario);
    }
}
