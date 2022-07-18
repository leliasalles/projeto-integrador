package com.dh.hospedagem.service;

import com.dh.hospedagem.DTO.CaracteristicasDTO;
import com.dh.hospedagem.DTO.ImagemDTO;
import com.dh.hospedagem.DTO.ProductDTO;
import com.dh.hospedagem.exceptionCustom.VerificaRegraNegocio;
import com.dh.hospedagem.model.Caracteristic;
import com.dh.hospedagem.model.Imagem;
import com.dh.hospedagem.model.Product;
import com.dh.hospedagem.repository.CaracteristicRepository;
import com.dh.hospedagem.repository.CategoryRepository;
import com.dh.hospedagem.repository.ImagemRepository;
import com.dh.hospedagem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImagemRepository imagemRepository;

    @Autowired
    private CaracteristicRepository caracteristicRepository;

    @Transactional(readOnly = true )
    public Page<ProductDTO> buscarTodos(PageRequest pageRequest){
        Page<Product> resultado = productRepository.findAll(pageRequest);
        return  resultado.map(product1 -> new ProductDTO(product1));
    }


    @Transactional(readOnly = true)
    public ProductDTO buscarPorId(int id){
        Optional<Product> product = productRepository.findById(id);
        Product entidade = product.orElseThrow(() -> new EntityExistsException("Não encontrado!"));
        return new ProductDTO(entidade);
    }

    // salva as entidades para os DTO do produto
    private void salvaEntidade(ProductDTO productDTO,
                               Product entidade){
        entidade.setName(productDTO.getName());
        entidade.setDescription(productDTO.getDescription());
        entidade.setCidades(productDTO.getCidades());
        entidade.setCategorias(productDTO.getCategorias());
        entidade.getImagem().clear();
        for(ImagemDTO imagemDTO:productDTO.getImagem()){
            Imagem imagem = imagemRepository.getById(imagemDTO.getId());
            entidade.getImagem().add(imagem);
        }
        entidade.getCaracteristic().clear();
        for(CaracteristicasDTO caracteristicasDTO:productDTO.getCaracteristicas()){
            Caracteristic caracteristic = caracteristicRepository.getById(caracteristicasDTO.getId());
            entidade.getCaracteristic().add(caracteristic);
        }

    }

    //salvar
    @Transactional
    public ProductDTO insert(ProductDTO productDTO){
        Product entidade = new Product();
        salvaEntidade(productDTO, entidade);
        entidade = productRepository.save(entidade);
        return new ProductDTO(entidade);
    }

    // Update
    @Transactional
    public ProductDTO update(Integer id, ProductDTO productDTO){
        try{
            Product entidade = productRepository.getById(id);
            salvaEntidade(productDTO, entidade);
            entidade = productRepository.save(entidade);
            return new ProductDTO(entidade);
        } catch (EntityNotFoundException e){
            throw new VerificaRegraNegocio("Id não existe");
        }

    }

    public void excluir(Integer productId){
        productRepository.deleteById(productId);
    }

    public Page<Product> search(String cidade, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "name");

        return productRepository.obterProdutosPorIdCidade(cidade.toLowerCase(), pageRequest);
    }
}
