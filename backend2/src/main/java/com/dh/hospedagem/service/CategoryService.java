package com.dh.hospedagem.service;

import com.dh.hospedagem.exceptionCustom.VerificaRegraNegocio;
import com.dh.hospedagem.model.Category;
import com.dh.hospedagem.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.dh.hospedagem.adicionais.ValidadorURL.valida;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // anotação do tudo ou nada, se ocorrer um erro o bd não é modificado
    @Transactional
    public Category salvar(Category category){
        //  se o nome já estiver cadastrado para a categoria não salvar, ou atualização
        boolean nomeUsado = categoryRepository.findByName(category.getName()).
                stream().anyMatch(nomeExiste -> !nomeExiste.equals(category));

        // se a url não for válida mostrar um mensagem
        if(!valida(category.getUrl_imagem())){
            throw new VerificaRegraNegocio("Url não é válida!");
        }

        // se o nome já estiver cadastrado, mostrar uma mensagem
        if(nomeUsado){
            // lança uma exceção se o nome da categoria já existe
            throw new VerificaRegraNegocio("Nome já cadastrado!");
        }

        return categoryRepository.save(category);
    }

    public void excluir(Integer categoriaId){
        // precisa verificar se a categoria não tem produtos, caso tenha não deletar
        categoryRepository.deleteById(categoriaId);
    }

}
