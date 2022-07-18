package com.dh.hospedagem;

import com.dh.hospedagem.model.Funcao;
import com.dh.hospedagem.repository.FuncaoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableJpaRepositories
@EnableAutoConfiguration
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
public class ProjetoPiHospedagemApplication implements CommandLineRunner {

	private final FuncaoRepository funcaoRepository;

	public ProjetoPiHospedagemApplication(FuncaoRepository funcaoRepository) {
		this.funcaoRepository = funcaoRepository;
	}


	public static void main(String[] args) {
		SpringApplication.run(ProjetoPiHospedagemApplication.class, args);
	}


	@Bean
	public PasswordEncoder getPasswordEncoder(){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Override
	public void run(String... args) throws Exception {
//		Funcao funcao = new Funcao();
//		funcao.setNome("ROLE_ADMIN");
//		funcaoRepository.save(funcao);
//
//		Funcao funcao1 = new Funcao();
//		funcao.setNome("ROLE_CLIENTE");
//		funcaoRepository.save(funcao1);
//
//		Funcao funcao2 = new Funcao();
//		funcao.setNome("ROLE_DONOIMOVEL");
//		funcaoRepository.save(funcao2);

	}
}
