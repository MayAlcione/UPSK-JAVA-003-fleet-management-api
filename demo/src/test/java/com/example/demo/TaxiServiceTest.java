package com.example.demo;

import com.example.demo.models.Taxi;
import com.example.demo.repositories.TaxiRepository;
import com.example.demo.services.TaxiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TaxiServiceTest {

    @Mock
    private TaxiRepository taxiRepository;

    @InjectMocks
    private TaxiService taxiService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllTaxis() {
        // Arrange
        List<Taxi> taxis = new ArrayList<>();
        taxis.add(new Taxi(1L, "ABC123"));
        Page<Taxi> expectedPage = new PageImpl<>(taxis);
        Pageable pageable = PageRequest.of(0, 10);
        when(taxiRepository.findAll(pageable)).thenReturn(expectedPage);

        // Act
        Page<Taxi> resultPage = taxiService.getAllTaxis(pageable);

        // Assert
        assertEquals(expectedPage, resultPage);
        verify(taxiRepository, times(1)).findAll(pageable);
    }
}

