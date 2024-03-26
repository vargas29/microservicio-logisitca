package com.microserviciopersonacliente.services;

import com.microserviciopersonacliente.dtos.CustomerDTO;
import com.microserviciopersonacliente.excepciones.BadRequestExceptionException;
import com.microserviciopersonacliente.excepciones.DataNotFoundException;
import com.microserviciopersonacliente.repositories.CustomerDTORepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerDTORepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private CustomerDTO customerDTO;

    @BeforeEach
    void setUp() {
        customerDTO = new CustomerDTO();
        customerDTO.setIdentificador("1");
        customerDTO.setNombre("Cliente 1");
        // Define más propiedades del cliente si es necesario para tus pruebas
    }

    @Test
    void testCreateCustomer() {
        when(customerRepository.existsById("1")).thenReturn(false);
        when(customerRepository.save(customerDTO)).thenReturn(customerDTO);

        CustomerDTO result = customerService.createCustomer(customerDTO);

        assertEquals(customerDTO, result);
    }

    @Test
    void testCreateCustomer_WithExistingId() {
        when(customerRepository.existsById("1")).thenReturn(true);

        assertThrows(BadRequestExceptionException.class, () -> customerService.createCustomer(customerDTO));
    }

    @Test
    void testGetCustomerById() {
        when(customerRepository.findById("1")).thenReturn(Optional.of(customerDTO));

        CustomerDTO result = customerService.getCustomerById("1");

        assertEquals(customerDTO, result);
    }

    @Test
    void testGetCustomerById_NotFound() {
        when(customerRepository.findById("2")).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> customerService.getCustomerById("2"));
    }

    // Escribe más pruebas para los otros métodos de CustomerService según sea necesario
}
