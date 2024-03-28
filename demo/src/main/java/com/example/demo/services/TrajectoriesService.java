// Importaciones necesarias para las clases y anotaciones utilizadas en la clase TrajectoriesService.
package com.example.demo.services;

import com.example.demo.models.Trajectories;
import com.example.demo.repositories.TrajectoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

// Esta clase proporciona servicios relacionados con la entidad Trajectories.
@Service
public class TrajectoriesService {

    // Inyección de dependencia del repositorio TrajectoriesRepository.
    private final TrajectoriesRepository trajectoriesRepository;

    // Constructor que recibe un TrajectoriesRepository como parámetro e inicializa el campo trajectoriesRepository.
    @Autowired
    public TrajectoriesService(TrajectoriesRepository trajectoriesRepository ) {
        this.trajectoriesRepository = trajectoriesRepository;

    }

    // Método para obtener todas las trayectorias paginadas.
    public Page<Trajectories> getAllTrajectories (Pageable pageable){

        return trajectoriesRepository.findAll(pageable);
    }

    // Método para buscar trayectorias por ID de taxi y fecha.
    public Page<Trajectories> findByDateAndId(int taxi_id, String dateString, Pageable pageable) {
        return trajectoriesRepository.findByTaxiIdAndDate(taxi_id, dateString, pageable);
    }


    // Método privado para crear un objeto Pageable utilizando el número de página y el tamaño de página dados.
    private Pageable createPageRequestUsing(int page, int size) {
        return PageRequest.of(page, size);
    }
    // Método para obtener la última ubicación reportada de cada taxi.
    public Page<Trajectories> getLastLocation (int page, int size) {
        // Crear objeto Pageable para la paginación.
        Pageable pageRequest = createPageRequestUsing(page, size);
        // Se llama al método findLastLocation() del repositorio TrajectoriesRepository para obtener todas las ubicaciones reportadas.
        List<Trajectories> allCustomers = trajectoriesRepository.findLastLocation();
        // Calcular el rango de índices de las ubicaciones a devolver.
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), allCustomers.size());
        // Obtener la lista de ubicaciones que corresponden a la página solicitada.
        List<Trajectories> pageContent = allCustomers.subList(start, end);
        // Obtener la lista completa de la última ubicación de cada taxi.
        List<Trajectories> lastLocation = trajectoriesRepository.findLastLocation();
        // Crear y devolver una página de resultados.
        return new PageImpl<>( pageContent, pageRequest, lastLocation.size());
    }


}
