// Importaciones necesarias para las clases y anotaciones utilizadas en la interfaz TrajectoriesRepository.
package com.example.demo.repositories;

import com.example.demo.models.Trajectories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// Esta interfaz define un repositorio de datos para la entidad Trajectories.
public interface TrajectoriesRepository extends PagingAndSortingRepository<Trajectories, Long> {

    // Consulta personalizada para buscar trayectorias por ID de taxi y fecha.
    @Query(value = "SELECT * FROM trajectories t WHERE t.taxi_id = :taxi_id AND TO_CHAR(t.date, 'YYYY-MM-DD') = :date", nativeQuery = true)
    Page<Trajectories> findByTaxiIdAndDate(@Param("taxi_id") int taxi_id, @Param("date") String date, Pageable pageable);

    // Consulta personalizada para encontrar la última ubicación reportada de cada taxi.
    @Query(value = "SELECT ID, TAXI_ID, date, LONGITUDE, LATITUDE FROM (SELECT ID, TAXI_ID, date, LONGITUDE, LATITUDE, ROW_NUMBER() OVER (PARTITION BY TAXI_ID ORDER BY date DESC) AS rn FROM TRAJECTORIES ) AS subquery WHERE rn = 1", nativeQuery = true)
    List<Trajectories> findLastLocation();
}
