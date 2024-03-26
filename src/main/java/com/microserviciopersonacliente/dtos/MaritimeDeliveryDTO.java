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
public class MaritimeDeliveryDTO extends DeliveryDTO{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El puerto de entrega es obligatorio")
    private String portDelivery;

    @NotBlank(message = "El número de flota es obligatorio")
    @Pattern(regexp = "[A-Z]{3}\\d{4}[A-Z]", message = "El formato del número de flota debe ser de 3 letras seguidas de 4 números y una letra al final")
    private String fleetNumber;


}
