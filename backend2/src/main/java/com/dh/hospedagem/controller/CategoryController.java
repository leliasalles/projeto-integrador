package com.dh.hospedagem.controller;

import com.dh.hospedagem.model.Category;
import com.dh.hospedagem.repository.CategoryRepository;
import com.dh.hospedagem.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@AllArgsConstructor
@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    // buscar listar todas as categorias
    @GetMapping
    public List<Category> listar() {
        return categoryRepository.findAll();
    }

    // buscar categoria por id, o atulizar pode ser colocado no service tmb, mas não precisa
    @GetMapping("/{categoriaId}")
    public ResponseEntity<Category> buscar(@PathVariable Integer categoriaId){
        return categoryRepository.findById(categoriaId)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // adicionar uma nova categoria
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category adicionar(@Valid @RequestBody Category category){
        return categoryService.salvar(category);
    }

    // atualiza o conteudo da categoria
    @PutMapping("/{categoriaId}")
    public ResponseEntity<Category> atualizar(@Valid @PathVariable Integer categoriaId,
                                              @RequestBody Category category){
        if(!categoryRepository.existsById(categoriaId)){
            return ResponseEntity.notFound().build();
        }
        category.setId(categoriaId);
        category = categoryService.salvar(category);
        return ResponseEntity.ok(category);
    }

    // deleta uma categoria
    @DeleteMapping("/{categoriaId}")
    public ResponseEntity<Void> delete(@PathVariable Integer categoriaId){

        // se a categoria não existe
        if(!categoryRepository.existsById(categoriaId)){
            return ResponseEntity.notFound().build();
        }

        // se a categoria existir
        categoryService.excluir(categoriaId);
        return ResponseEntity.noContent().build();
    }

}
