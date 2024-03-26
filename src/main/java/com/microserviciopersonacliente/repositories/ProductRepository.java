package com.microserviciopersonacliente.repositories;

import com.microserviciopersonacliente.dtos.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductDTO, Long> {
}