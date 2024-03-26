package com.microserviciopersonacliente.dtos;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class ProductDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoProducto;


    @NotBlank(message = "El campo nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El campo descripcion no puede estar vacío")
    private String descripcion;

    @NotNull(message = "El campo precio no puede estar vacío")
    private Double precio;
}
