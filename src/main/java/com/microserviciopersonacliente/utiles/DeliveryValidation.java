package com.microserviciopersonacliente.utiles;

import com.microserviciopersonacliente.dtos.MaritimeDeliveryDTO;
import com.microserviciopersonacliente.dtos.TerrestrialDeliveryDTO;
import com.microserviciopersonacliente.excepciones.BadRequestExceptionException;
import com.microserviciopersonacliente.excepciones.DataNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class DeliveryValidation
{
    public void validateTerrestrialDelivery(TerrestrialDeliveryDTO deliveryDTO) {

        if (!deliveryDTO.getVehiclePlate().matches("[A-Z]{3}\\d{3}")) {
            throw new BadRequestExceptionException("El formato del número de La placa no es válido");
        }
        if (!deliveryDTO.getGuideNumber().matches("\\w{10}")) {
            throw new BadRequestExceptionException("El formato del número de guia no es válido");
        }



    }
    public void validateMaritimeDelivery(MaritimeDeliveryDTO deliveryDTO) {

        if (!deliveryDTO.getPortDelivery().matches("[A-Z]{3}\\d{4}[A-Z]")) {
            throw new BadRequestExceptionException("El formato del número de flota debe ser de 3 letras seguidas de 4 números y una letra al final");
        }
        if (!deliveryDTO.getGuideNumber().matches("\\w{10}")) {
            throw new BadRequestExceptionException("El formato del número de guia no es válido");
        }


    }
}
