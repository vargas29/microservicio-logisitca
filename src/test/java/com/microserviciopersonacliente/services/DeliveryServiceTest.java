package com.microserviciopersonacliente.services;

import com.microserviciopersonacliente.dtos.MaritimeDeliveryDTO;
import com.microserviciopersonacliente.dtos.TerrestrialDeliveryDTO;
import com.microserviciopersonacliente.excepciones.BadRequestExceptionException;
import com.microserviciopersonacliente.repositories.*;
import com.microserviciopersonacliente.services.DeliveryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeliveryServiceTest {

    @Mock
    private TerrestrialDeliveryRepository terrestrialDeliveryRepository;

    @Mock
    private MaritimeDeliveryRepository maritimeDeliveryRepository;

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CustomerDTORepository clientRepository;

    @InjectMocks
    private DeliveryService deliveryService;

    @Test
    public void testCreateTruckDelivery_Success() {
        // Arrange
        TerrestrialDeliveryDTO terrestrialDelivery = new TerrestrialDeliveryDTO();
        terrestrialDelivery.setClientId("existingClientId");
        terrestrialDelivery.setProduct("existingProductId");
        terrestrialDelivery.setProductQuantity(5);
        terrestrialDelivery.setShippingPrice(100.0);

        // Act
        deliveryService.createTruckDelivery(terrestrialDelivery);

        // Assert
        verify(clientRepository, times(1)).existsById("existingClientId");
        verify(productRepository, times(1)).existsById(5L);
        verify(terrestrialDeliveryRepository, times(1)).save(terrestrialDelivery);
    }

    @Test
    public void testCreateTruckDelivery_ProductQuantityGreaterThanTen_DiscountApplied() {
        // Arrange
        TerrestrialDeliveryDTO terrestrialDelivery = new TerrestrialDeliveryDTO();
        terrestrialDelivery.setClientId("existingClientId");
        terrestrialDelivery.setProduct("existingProductId");
        terrestrialDelivery.setProductQuantity(15);
        terrestrialDelivery.setShippingPrice(100.0);

        // Act
        deliveryService.createTruckDelivery(terrestrialDelivery);

        // Assert
        assertEquals(95.0, terrestrialDelivery.getShippingPrice());
        verify(clientRepository, times(1)).existsById("existingClientId");
        verify(productRepository, times(1)).existsById(5L);
        verify(terrestrialDeliveryRepository, times(1)).save(terrestrialDelivery);
    }

    @Test
    public void testCreateTruckDelivery_NonExistingClient_ExceptionThrown() {
        // Arrange
        TerrestrialDeliveryDTO terrestrialDelivery = new TerrestrialDeliveryDTO();
        terrestrialDelivery.setClientId("nonExistingClientId");

        // Act & Assert
        assertThrows(BadRequestExceptionException.class, () -> {
            deliveryService.createTruckDelivery(terrestrialDelivery);
        });

        verify(clientRepository, times(1)).existsById("nonExistingClientId");
        verifyNoInteractions(productRepository);
        verifyNoInteractions(terrestrialDeliveryRepository);
    }

    // Similar tests for other methods can be implemented following a similar approach.
}
