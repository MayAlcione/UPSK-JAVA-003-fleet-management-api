package com.example.demo;

import com.example.demo.models.Taxi;
import com.example.demo.models.Trajectories;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TrajectoriesTest {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        long id = 1L;
        Taxi taxi = new Taxi(1L, "ABC123");
        OffsetDateTime date = OffsetDateTime.now();
        double latitude = 40.7128;
        double longitude = -74.0060;

        // Act
        Trajectories trajectories = new Trajectories(id, taxi, date, latitude, longitude);

        // Assert
        assertNotNull(trajectories);
        assertEquals(id, trajectories.getId());
        assertEquals(taxi, trajectories.getTaxi());
        assertEquals(date, trajectories.getDate());
        assertEquals(latitude, trajectories.getLatitude());
        assertEquals(longitude, trajectories.getLongitude());
    }

    @Test
    public void testSetters() {
        // Arrange
        Trajectories trajectories = new Trajectories();
        long id = 1L;
        Taxi taxi = new Taxi(1L, "ABC123");
        OffsetDateTime date = OffsetDateTime.now();
        double latitude = 40.7128;
        double longitude = -74.0060;

        // Act
        trajectories.setId(id);
        trajectories.setTaxi(taxi);
        trajectories.setDate(date);
        trajectories.setLatitude(latitude);
        trajectories.setLongitude(longitude);

        // Assert
        assertEquals(id, trajectories.getId());
        assertEquals(taxi, trajectories.getTaxi());
        assertEquals(date, trajectories.getDate());
        assertEquals(latitude, trajectories.getLatitude());
        assertEquals(longitude, trajectories.getLongitude());
    }
}

