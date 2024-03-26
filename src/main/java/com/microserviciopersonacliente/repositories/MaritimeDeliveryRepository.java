package com.microserviciopersonacliente.repositories;

import com.microserviciopersonacliente.dtos.MaritimeDeliveryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaritimeDeliveryRepository extends JpaRepository<MaritimeDeliveryDTO, String> {
    // Aquí puedes agregar métodos de consulta adicionales si es necesario
}
