package com.dh.hospedagem.controller;

import com.dh.hospedagem.DTO.ProductDTO;
import com.dh.hospedagem.exceptionCustom.VerificaRegraNegocio;
import com.dh.hospedagem.model.Category;
import com.dh.hospedagem.model.Product;
import com.dh.hospedagem.repository.CategoryRepository;
import com.dh.hospedagem.repository.CidadeRepository;
import com.dh.hospedagem.repository.ProductCustomRepository;
import com.dh.hospedagem.repository.ProductRepository;
import com.dh.hospedagem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private final ProductCustomRepository productCustomRepository;

    public ProductController(ProductCustomRepository productCustomRepository) {
        this.productCustomRepository = productCustomRepository;
    }

    // funcionando
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> listar(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(value = "size", defaultValue = "5") Integer size){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<ProductDTO> productDTOS = productService.buscarTodos(pageRequest);
        return ResponseEntity.ok(productDTOS);
    }

    //
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDTO> adicionar(
            @RequestBody ProductDTO productDTO){
        productDTO = productService.insert(productDTO);
        URI iri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(productDTO.getId()).toUri();

        return ResponseEntity.created(iri).body(productDTO);
    }

    //
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> atualizar(@PathVariable Integer id,
                                                @RequestBody ProductDTO productDTO){
        productDTO = productService.update(id, productDTO);
        return ResponseEntity.ok().body(productDTO);
    }

    // funcionando
    @GetMapping("/{id}")
    public ProductDTO buscarPorId(@PathVariable Integer id){
        Optional<Product> product = productRepository.findById(id);
        Product entity = product.orElseThrow(() -> new VerificaRegraNegocio("Produto não encontrado!"));
       return new ProductDTO(entity);
    }

//    // busca por categoria os produtos
//    @GetMapping("/nomecategoria/{name}")
//    public List<Product> buscarPorCategoria(@PathVariable Integer id){
//        Optional<Object> product = productRepository.obterProdutosPorIdCategoria(id);
//        return productRepository.obterProdutosPorIdCategoria(id);
//    }

    // deleta o produto - funcionando
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deletar(@PathVariable Integer productId){
        if(!productRepository.existsById(productId)){
            return ResponseEntity.notFound().build();
        }

        productService.excluir(productId);
        return ResponseEntity.noContent().build();
    }

//    @Transactional(readOnly = true )
    @GetMapping("/cidade/{id}")
    public Page<ProductDTO> buscarTodosPorCidade(Pageable pageRequest, @PathVariable Integer id){

        Page<Product> resultado = productRepository.obterProdutosPorIdCategoria(pageRequest, id);
        return  resultado.map(product1 -> new ProductDTO(product1));
    }

    // procura por name e descricao
//    http://localhost:8080/products/search?cidade=apartamento
    @GetMapping("/search")
    public Page<Product> listarProdutosPorNome(@RequestParam("cidade") String cidade, @RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") int page,
                                               @RequestParam(
                                            value = "size",
                                            required = false,
                                            defaultValue = "10") int size){
            return productService.search(cidade, page, size);
    }

    // Ex de pesquisa
    // http://localhost:8080/products/pesquisar?name=Palace&id=3
    // http://localhost:8080/products/pesquisar?name=Palace
    //http://localhost:8080/products/pesquisar?id=3
    @GetMapping("/pesquisar")
    public List<Product> pesquisaPorNomeEID(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "nome", required = false) String nome){

        try {
            return productCustomRepository.pesquisa(id,nome);
        } catch (Exception e){
            throw new VerificaRegraNegocio("Produto não encontrado!");
        }
//        return productCustomRepository.pesquisa(id,nome);
    }

    //http://localhost:8080/products/pesquisa_cidade?cidade=Porto
    @GetMapping("/pesquisa_cidade")
    public List<Product> pesquisaProdutosPorCidade(@RequestParam(value = "cidade") String cidade){
        return productRepository.obterProdutosPorCidade(cidade);
    }

}
