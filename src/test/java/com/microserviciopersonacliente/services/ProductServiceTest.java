package com.microserviciopersonacliente.services;

import com.microserviciopersonacliente.dtos.ProductDTO;
import com.microserviciopersonacliente.excepciones.BadRequestExceptionException;
import com.microserviciopersonacliente.excepciones.DataNotFoundException;
import com.microserviciopersonacliente.repositories.ProductRepository;
import com.microserviciopersonacliente.utiles.Constantes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private ProductDTO productDTO;

    @BeforeEach
    public void setUp() {
        productDTO = new ProductDTO();

        productDTO.setDescripcion("Test Product");
        productDTO.setPrecio(10.0);
    }

    @Test
    public void testGetAllProducts() {
        List<ProductDTO> productList = new ArrayList<>();
        productList.add(productDTO);

        when(productRepository.findAll()).thenReturn(productList);

        List<ProductDTO> result = productService.getAllProducts();

        assertEquals(1, result.size());
        assertEquals(productDTO, result.get(0));
    }

    @Test
    public void testGetProductById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(productDTO));

        ProductDTO result = productService.getProductById(1L);

        assertEquals(productDTO, result);
    }

    @Test
    public void testGetProductById_NotFound() {
        assertThrows(DataNotFoundException.class, () -> productService.getProductById(2L));
    }

    @Test
    public void testCreateProduct() {
        when(productRepository.save(productDTO)).thenReturn(productDTO);

        ProductDTO result = productService.createProduct(productDTO);

        assertEquals(productDTO, result);
    }

    @Test
    public void testCreateProduct_WithId() {
        productDTO.setId(2L);

        assertThrows(BadRequestExceptionException.class, () -> productService.createProduct(productDTO));
    }



    @Test
    public void testUpdateProduct_NotFound() {
        assertThrows(DataNotFoundException.class, () -> productService.updateProduct(2L, new ProductDTO()));
    }

    @Test
    public void testDeleteProduct() {
        assertDoesNotThrow(() -> productService.deleteProduct(1L));

        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteProduct_NotFound() {
        doThrow(new RuntimeException()).when(productRepository).deleteById(2L);

        assertThrows(DataNotFoundException.class, () -> productService.deleteProduct(2L));
    }
}
