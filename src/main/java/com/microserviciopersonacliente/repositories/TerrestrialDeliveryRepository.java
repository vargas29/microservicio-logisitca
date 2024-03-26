package com.microserviciopersonacliente.repositories;


import com.microserviciopersonacliente.dtos.TerrestrialDeliveryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerrestrialDeliveryRepository extends JpaRepository<TerrestrialDeliveryDTO, Long> {
    // Aquí puedes agregar métodos de consulta adicionales si es necesario
}
