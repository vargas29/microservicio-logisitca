package com.microserviciopersonacliente.dtos;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@Data
public class CustomerDTO
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String identificador;
    private String nombre;
    private String direccion;
    private String ciudad;
    private String telefono;
    private String email;

}
