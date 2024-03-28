// Importaciones necesarias para las clases y anotaciones utilizadas en la clase Taxi.
package com.example.demo.models;

import jakarta.persistence.*;

// Esta clase representa la entidad Taxi en la base de datos.
@Entity
@Table(name = "taxis")
public class Taxi {

    // Identificador único de un taxi.
    @Id
    @Column(name= "id")
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Número de placa del taxi.
    @Column(name= "plate")
    private String plate;

    // Constructor vacío requerido por JPA
    public Taxi() {}

    // Constructor con parámetros que inicializa un objeto Taxi con un ID y número de placa específicos.
    public Taxi(Long id, String plate) {
        this.id = id;
        this.plate = plate;
    }

    // Métodos para obtener y establecer el ID del taxi.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Métodos para obtener y establecer el número de placa del taxi.
    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}
