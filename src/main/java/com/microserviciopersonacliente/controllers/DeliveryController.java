package com.microserviciopersonacliente.controllers;

import com.microserviciopersonacliente.dtos.DeliveryDTO;
import com.microserviciopersonacliente.dtos.MaritimeDeliveryDTO;
import com.microserviciopersonacliente.dtos.TerrestrialDeliveryDTO;
import com.microserviciopersonacliente.excepciones.BadRequestExceptionException;
import com.microserviciopersonacliente.services.DeliveryService;
import com.microserviciopersonacliente.utiles.DeliveryValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private DeliveryValidation deliveryValidation;
    @PostMapping("/terrestrial")
    public ResponseEntity<?> createTerrestrialDelivery(@RequestBody TerrestrialDeliveryDTO deliveryDTO) {

        try {
            deliveryValidation.validateTerrestrialDelivery(deliveryDTO);
            // Aquí podrías implementar la lógica para aplicar descuentos si corresponde
            deliveryService.createTruckDelivery(deliveryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(deliveryDTO);
        } catch (BadRequestExceptionException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la entrega terrestre.");
        }
    }

    @PostMapping("/maritime")
    public ResponseEntity<?> createMaritimeDelivery(@RequestBody MaritimeDeliveryDTO deliveryDTO) {
        try {
            // Aquí podrías implementar la lógica para aplicar descuentos si corresponde
            deliveryValidation.validateMaritimeDelivery(deliveryDTO);
                deliveryService.createMaritimeDelivery(deliveryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BadRequestExceptionException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la entrega terrestre.");
        }
    }


    @GetMapping("/client/{clientId}")
    public ResponseEntity<?> getDeliveriesByClientId(@PathVariable String clientId) {
        try {
            List<DeliveryDTO> deliveries = deliveryService.getAllDeliveriesByClientId(clientId);
            return ResponseEntity.ok(deliveries);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener las entregas del cliente.");
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getDeliveriesByProductId(@PathVariable String productId) {
        try {
            List<DeliveryDTO> deliveries = deliveryService.getAllDeliveriesByProductId(productId);
            return ResponseEntity.ok(deliveries);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener las entregas del producto.");
        }
    }
}
