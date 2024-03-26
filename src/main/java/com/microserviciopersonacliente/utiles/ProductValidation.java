package com.microserviciopersonacliente.utiles;

import com.microserviciopersonacliente.dtos.ProductDTO;
import com.microserviciopersonacliente.excepciones.BadRequestExceptionException;
import org.springframework.stereotype.Service;

@Service
public class ProductValidation {

    public void validateProduct(ProductDTO productDTO) {


        if (productDTO.getPrecio() == null) {
            throw new BadRequestExceptionException("Price cannot be null");
        }
        if (productDTO.getTipoProducto() == null || !productDTO.getTipoProducto().equals("1") && !productDTO.getTipoProducto().equals("2") ) {
            throw new BadRequestExceptionException("Tipo de producto debe ser 1 o 2");
        }
    }
}
