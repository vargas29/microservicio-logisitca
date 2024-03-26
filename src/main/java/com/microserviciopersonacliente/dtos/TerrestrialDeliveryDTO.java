package com.microserviciopersonacliente.dtos;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
@Entity
@Data
public class TerrestrialDeliveryDTO extends DeliveryDTO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La bodega de entrega es obligatoria")
    private String warehouseDelivery;

    @NotBlank(message = "La placa del vehículo es obligatoria")
    @Pattern(regexp = "[A-Z]{3}\\d{3}", message = "El formato de la placa del vehículo debe ser de 3 letras seguidas de 3 números")
    private String vehiclePlate;
}
