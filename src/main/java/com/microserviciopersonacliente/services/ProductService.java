package com.microserviciopersonacliente.services;

import com.microserviciopersonacliente.dtos.ProductDTO;
import com.microserviciopersonacliente.excepciones.BadRequestExceptionException;
import com.microserviciopersonacliente.excepciones.DataNotFoundException;

import com.microserviciopersonacliente.repositories.ProductRepository;
import com.microserviciopersonacliente.utiles.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductDTO getProductById(Long id) {
        Optional<ProductDTO> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElseThrow(() -> new DataNotFoundException(Constantes.NOT_FOUND_PRODUCTO + id));
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        if (productDTO.getId() != null) {
            throw new BadRequestExceptionException(Constantes.ERROR_FOUND_PRODUCTO);
        }
        return productRepository.save(productDTO);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        getProductById(id); // Lanza excepción si el producto no existe
        productDTO.setId(id);
        return productRepository.save(productDTO);
    }

    public void deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            throw new DataNotFoundException(Constantes.NOT_FOUND_PRODUCTO + id);
        }
    }
}
