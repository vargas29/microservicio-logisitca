package com.microserviciopersonacliente.services;

import com.microserviciopersonacliente.dtos.*;
import com.microserviciopersonacliente.excepciones.BadRequestExceptionException;
import com.microserviciopersonacliente.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryService {

    @Autowired
    private TerrestrialDeliveryRepository truckDeliveryRepository;

    @Autowired
    private MaritimeDeliveryRepository maritimeDeliveryRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerDTORepository clientRepository;


    public void createTruckDelivery(TerrestrialDeliveryDTO truckDelivery) {
        validateExistClientId(truckDelivery.getClientId());
        validateExistProductId(truckDelivery.getProduct());
        if (truckDelivery.getProductQuantity() > 10) {
            // Calcular el descuento para la entrega terrestre (5%)
            double shippingPrice = truckDelivery.getShippingPrice();
            double discount = shippingPrice * 0.05;
            truckDelivery.setShippingPrice(shippingPrice - discount);
        }
        truckDeliveryRepository.save(truckDelivery);
    }

    public void createMaritimeDelivery(MaritimeDeliveryDTO maritimeDelivery) {
        validateExistClientId(maritimeDelivery.getClientId());
        validateExistProductId(maritimeDelivery.getProduct());
        if (maritimeDelivery.getProductQuantity() > 10) {
            // Calcular el descuento para la entrega marítima (3%)
            double shippingPrice = maritimeDelivery.getShippingPrice();
            double discount = shippingPrice * 0.03;
            maritimeDelivery.setShippingPrice(shippingPrice - discount);
        }
        maritimeDeliveryRepository.save(maritimeDelivery);
    }


    private void validateExistClientId(String clientId){
        if (!clientRepository.existsById(clientId)) {
            throw new BadRequestExceptionException("El cliente con el ID proporcionado no existe");
        }

    }
    private void validateExistProductId(String productId){

        // Verificar si el producto existe
        if (!productRepository.existsById(Long.valueOf(productId))) {
            throw new BadRequestExceptionException("El producto con el ID proporcionado no existe");
        }
    }
    public List<DeliveryDTO> getAllDeliveriesByClientId(String clientId) {
        validateExistClientId(clientId);
        List<DeliveryDTO> allDeliveries = new ArrayList<>();

        // Agregar deliveries terrestres
        allDeliveries.addAll(deliveryRepository.findTerrestrialDeliveriesByClientId(clientId));

        // Agregar deliveries marítimos
        allDeliveries.addAll(deliveryRepository.findMaritimeDeliveriesByClientId(clientId));

        return allDeliveries;
    }
    public List<DeliveryDTO> getAllDeliveriesByProductId(String ProductId) {
        validateExistProductId(ProductId);
        List<DeliveryDTO> allDeliveries = new ArrayList<>();

        // Agregar deliveries terrestres asociados al producto
        allDeliveries.addAll(deliveryRepository.findTerrestrialDeliveriesByProduct(ProductId));

        // Agregar deliveries marítimos asociados al producto
        allDeliveries.addAll(deliveryRepository.findMaritimeDeliveriesByProduct(ProductId));

        return allDeliveries;
    }

}

