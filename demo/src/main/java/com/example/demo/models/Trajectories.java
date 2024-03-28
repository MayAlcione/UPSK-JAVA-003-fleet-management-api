// Importaciones necesarias para las clases y anotaciones utilizadas en la clase Trajectories.
package com.example.demo.models;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

// Esta clase representa la entidad Trajectories en la base de datos.
@Entity
@Table(name = "trajectories")
public class Trajectories {

    // Identificador único de una trayectoria.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Referencia al objeto Taxi al que pertenece esta trayectoria.
    @ManyToOne()
    @JoinColumn(name= "taxi_id")
    private Taxi taxi;

    // Fecha y hora en la que se registró la trayectoria.
    private OffsetDateTime date;

    // Latitud de la ubicación de la trayectoria.
    private double latitude;

    // Longitud de la ubicación de la trayectoria.
    private double longitude;

    // Constructor vacío requerido por JPA.
    public Trajectories() {}

    // Constructor con parámetros que inicializa un objeto Trajectories con valores específicos.
    public Trajectories(long id, Taxi taxi, OffsetDateTime date, double latitude, double longitude) {
        this.id = id;
        this.taxi = taxi;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Métodos para obtener y establecer el ID de la trayectoria.
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    // Métodos para obtener y establecer el objeto Taxi asociado a la trayectoria.
    public Taxi getTaxi() {
        return taxi;
    }
    public void setTaxi(Taxi taxi) {
        this.taxi = taxi;
    }

    // Métodos para obtener y establecer la fecha y hora de la trayectoria.
    public OffsetDateTime getDate() {
        return date;
    }
    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    // Métodos para obtener y establecer la latitud de la trayectoria.
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    // Métodos para obtener y establecer la longitud de la trayectoria.
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
