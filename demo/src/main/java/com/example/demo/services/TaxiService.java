// Importaciones necesarias para las clases y anotaciones utilizadas en la clase TaxiService.
package com.example.demo.services;

import com.example.demo.models.Taxi;
import com.example.demo.repositories.TaxiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

// Esta clase proporciona servicios relacionados con la entidad Taxi.
@Service
public class TaxiService {
    // Inyección de dependencia del repositorio TaxiRepository.
    private final TaxiRepository taxiRepository;
// Constructor que recibe un TaxiRepository como parámetro e inicializa el campo taxiRepository.
    @Autowired
    public TaxiService (TaxiRepository taxiRepository) {
        this.taxiRepository= taxiRepository;
    }

    // Método para obtener todos los taxis paginados.
    public Page<Taxi> getAllTaxis(Pageable pageable){

        return taxiRepository.findAll(pageable);
    }
}



