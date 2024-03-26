package com.microserviciopersonacliente.dtos;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
@Entity
@Data
public class DeliveryDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El tipo de producto es obligatorio")
    private String productType;

    @NotBlank(message = "El producto es obligatorio")
    private String product;

    @NotNull(message = "La cantidad de producto es obligatoria")
    private int productQuantity;

    // Si las fechas se manejan como cadenas, puedes validarlas con expresiones regulares
    @NotBlank(message = "La fecha de registro es obligatoria")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "El formato de la fecha de registro debe ser YYYY-MM-DD")
    private String registrationDate;

    @NotBlank(message = "La fecha de entrega es obligatoria")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "El formato de la fecha de entrega debe ser YYYY-MM-DD")
    private String deliveryDate;

    @NotNull(message = "El precio de envío es obligatorio")
    private double shippingPrice;

    @NotBlank(message = "El número de guía es obligatorio")
    @Pattern(regexp = "\\w{10}", message = "El número de guía debe tener 10 caracteres alfanuméricos")
    private String guideNumber;

    @NotBlank(message = "El ID del cliente es obligatorio")
    private String clientId;
}
