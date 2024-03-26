package com.microserviciopersonacliente.services;


import com.microserviciopersonacliente.dtos.CustomerDTO;
import com.microserviciopersonacliente.excepciones.BadRequestExceptionException;
import com.microserviciopersonacliente.excepciones.DataNotFoundException;
import com.microserviciopersonacliente.repositories.CustomerDTORepository;
import com.microserviciopersonacliente.utiles.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerDTORepository customerRepository;

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        if (customerRepository.existsById(customerDTO.getIdentificador())) {
            throw new BadRequestExceptionException("El cliente con ID " + customerDTO.getIdentificador() + " ya existe");
        }
        return customerRepository.save(customerDTO);
    }


    public CustomerDTO getCustomerById(String id) {
        Optional<CustomerDTO> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.orElseThrow(() -> new DataNotFoundException(Constantes.NOT_FOUND_CUSTOMER + id));
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll();
    }

    public CustomerDTO updateCustomer(String id, CustomerDTO customerDTO) {
        if (!customerRepository.existsById(id)) {
            throw new DataNotFoundException(Constantes.NOT_FOUND_CUSTOMER + id);
        }
        customerDTO.setIdentificador(id); // Asegúrate de establecer el ID del cliente correctamente
        return customerRepository.save(customerDTO);
    }

    public void deleteCustomer(String id) {
        if (!customerRepository.existsById(id)) {
            throw new DataNotFoundException(Constantes.NOT_FOUND_CUSTOMER + id);
        }
        customerRepository.deleteById(id);
    }
}
