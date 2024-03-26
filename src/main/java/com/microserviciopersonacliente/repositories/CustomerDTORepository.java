package com.microserviciopersonacliente.repositories;

import com.microserviciopersonacliente.dtos.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDTORepository extends JpaRepository<CustomerDTO, String> {
    CustomerDTO findByIdentificador(String identificador);
}
