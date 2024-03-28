// Importaciones necesarias para las clases y anotaciones utilizadas en el controlador.
package com.example.demo.controllers;

import com.example.demo.models.Trajectories;
import com.example.demo.services.TrajectoriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

// Este controlador maneja las solicitudes HTTP relacionadas con las trayectorias de los taxis.
@RestController
@RequestMapping("/trajectories")
public class TrajectoriesController {
    // Se utiliza @Autowired para inyectar una instancia de TrajectoriesService en el controlador.
    @Autowired
    private final TrajectoriesService trajectoriesService;

    // Constructor del controlador que recibe un objeto TrajectoriesService como parámetro e inicializa el campo trajectoriesService.
    public TrajectoriesController(TrajectoriesService trajectoriesService) {
        this.trajectoriesService = trajectoriesService;
    }

    // Documentación del método getAllTrajectories() con Swagger utilizando la anotación @Operation para resumir la operación del método y @ApiResponses para describir las respuestas posibles.
    @Operation(summary = "Get-All-Trajectories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the trajectories",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Trajectories.class))}),
            @ApiResponse(responseCode = "400", description = "Object trajectories invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Trajectories not found",
                    content = @Content)
    })

    // Maneja las solicitudes GET en la ruta "/trajectories/all" para obtener todas las trayectorias de los taxis.
    // El método acepta dos parámetros opcionales: "page" para el número de página y "size" para el tamaño de la página.
    @GetMapping("/all")
    public Page<Trajectories> getAllTrajectories(@Parameter(description = "Page number, default is 0") @RequestParam(defaultValue = "0") int page,
                                                 @Parameter(description = "Number of items per page, default is 10") @RequestParam(defaultValue = "10") int size) {
        // Crea un objeto Pageable utilizando los parámetros de página y tamaño recibidos.
        Pageable pageable = PageRequest.of(page, size);
        // Llama al método getAllTrajectories(pageable) del servicio TrajectoriesService para obtener una página de resultados de trayectorias.
        return trajectoriesService.getAllTrajectories(pageable);
    }

    // Documentación del método findByDateAndId() con Swagger utilizando la anotación @Operation para resumir la operación del método y @ApiResponses para describir las respuestas posibles.
    @Operation(summary = "Get-Locations-Of-A-Taxi-By-ID-And-Date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the taxi locations",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Trajectories.class))}),
            @ApiResponse(responseCode = "404", description = "Taxi locations not found",
                    content = @Content)
    })

    // Maneja las solicitudes GET en la ruta "/trajectories/byDateAndId" para obtener las trayectorias de un taxi por ID y fecha.
    // El método acepta cuatro parámetros: "taxi_id" para el ID del taxi, "date" para la fecha en formato YYYY-MM-DD, "page" para el número de página y "size" para el tamaño de la página.
    @GetMapping("byDateAndId")
    public Page<Trajectories> findByDateAndId(@Parameter(description = "Taxi ID") @RequestParam(name = "taxi_id") int taxi_id,
                                              @Parameter(description = "Date in format YYYY-MM-DD") @RequestParam(name = "date") String dateString,
                                              @Parameter(description = "Page number, default is 0") @RequestParam(defaultValue = "0") int page,
                                              @Parameter(description = "Number of items per page, default is 10") @RequestParam(defaultValue = "10") int size) {
        // Crea un objeto Pageable utilizando los parámetros de página y tamaño recibidos.
        Pageable pageable = PageRequest.of(page, size);
        // Llama al método findByDateAndId(taxi_id, dateString, pageable) del servicio TrajectoriesService para obtener una página de resultados de trayectorias.
        return trajectoriesService.findByDateAndId(taxi_id, dateString, pageable);
    }

    // Documentación del método getLastLocation() con Swagger utilizando la anotación @Operation para resumir la operación del método y @ApiResponses para describir las respuestas posibles.
    @Operation(summary = "Get-Last-Locations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the taxi last locations",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Trajectories.class))}),
            @ApiResponse(responseCode = "404", description = "Taxi last locations not found",
                    content = @Content)
    })

    // Maneja las solicitudes GET en la ruta "/trajectories/last-Location" para obtener la última ubicación reportada de cada taxi.
    // El método acepta dos parámetros opcionales: "page" para el número de página y "size" para el tamaño de la página.
    @GetMapping("last-Location")
    public Page<Trajectories> getLastLocation(@Parameter(description = "Page number, default is 0") @RequestParam(defaultValue = "0") int page,
                                              @Parameter(description = "Number of items per page, default is 10") @RequestParam(defaultValue = "10") int size) {
        // Llama al método getLastLocation(page, size) del servicio TrajectoriesService para obtener una página de resultados de las últimas ubicaciones de los taxis.
        return trajectoriesService.getLastLocation(page, size);
    }


}




