package com.example.demo;

import com.example.demo.models.Taxi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaxiTest {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        Long id = 1L;
        String plate = "ABC123";

        // Act
        Taxi taxi = new Taxi(id, plate);

        // Assert
        assertNotNull(taxi);
        assertEquals(id, taxi.getId());
        assertEquals(plate, taxi.getPlate());
    }

    @Test
    public void testSetters() {
        // Arrange
        Taxi taxi = new Taxi();
        Long id = 1L;
        String plate = "XYZ987";

        // Act
        taxi.setId(id);
        taxi.setPlate(plate);

        // Assert
        assertEquals(id, taxi.getId());
        assertEquals(plate, taxi.getPlate());
    }
}

