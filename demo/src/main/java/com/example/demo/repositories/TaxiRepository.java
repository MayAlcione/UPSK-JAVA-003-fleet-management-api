// Importaciones necesarias para las clases y anotaciones utilizadas en la interfaz TaxiRepository.
package com.example.demo.repositories;

import com.example.demo.models.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;

// Esta interfaz define un repositorio de datos para la entidad Taxi.
public interface TaxiRepository extends JpaRepository<Taxi, Long> {
}
