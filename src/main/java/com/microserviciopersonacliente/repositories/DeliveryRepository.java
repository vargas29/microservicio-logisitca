package com.microserviciopersonacliente.repositories;

import com.microserviciopersonacliente.dtos.MaritimeDeliveryDTO;
import com.microserviciopersonacliente.dtos.TerrestrialDeliveryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.microserviciopersonacliente.dtos.DeliveryDTO;
import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryDTO, Long> {
    List<TerrestrialDeliveryDTO> findTerrestrialDeliveriesByClientId(String clientId);
    List<MaritimeDeliveryDTO> findMaritimeDeliveriesByClientId(String clientId);

    List<TerrestrialDeliveryDTO> findTerrestrialDeliveriesByProduct(String product);
    List<MaritimeDeliveryDTO> findMaritimeDeliveriesByProduct(String product);
}
