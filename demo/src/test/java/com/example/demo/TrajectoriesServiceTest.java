package com.example.demo;

import com.example.demo.models.Trajectories;
import com.example.demo.repositories.TrajectoriesRepository;
import com.example.demo.services.TrajectoriesService;
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

public class TrajectoriesServiceTest {

    @Mock
    private TrajectoriesRepository trajectoriesRepository;

    @InjectMocks
    private TrajectoriesService trajectoriesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllTrajectories() {
        // Arrange
        List<Trajectories> trajectoriesList = new ArrayList<>();
        trajectoriesList.add(new Trajectories());
        Page<Trajectories> expectedPage = new PageImpl<>(trajectoriesList);
        Pageable pageable = PageRequest.of(0, 10);
        when(trajectoriesRepository.findAll(pageable)).thenReturn(expectedPage);

        // Act
        Page<Trajectories> resultPage = trajectoriesService.getAllTrajectories(pageable);

        // Assert
        assertEquals(expectedPage, resultPage);
        verify(trajectoriesRepository, times(1)).findAll(pageable);
    }

}

