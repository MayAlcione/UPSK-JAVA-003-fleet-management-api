package com.example.demo;

import static org.mockito.Mockito.*;

import com.example.demo.models.Taxi;
import com.example.demo.services.TaxiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;



@SpringBootTest
@AutoConfigureMockMvc
public class TaxiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaxiService taxiService;

    @BeforeEach
    public void setUp() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Taxi> taxis = new PageImpl<>(List.of(new Taxi(), new Taxi()));
        when(taxiService.getAllTaxis(pageable)).thenReturn(taxis);
    }

    @Test
    public void testGetAllTaxis() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/taxi/all")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").exists());
    }

    @Test
    public void testGetAllTaxisService() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);

        // Act
        Page<Taxi> result = taxiService.getAllTaxis(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
    }
}
