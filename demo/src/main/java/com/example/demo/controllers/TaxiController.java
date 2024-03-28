// Importaciones necesarias para las clases y anotaciones utilizadas en el controlador.
package com.example.demo.controllers;

import com.example.demo.models.Taxi;
import com.example.demo.services.TaxiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

// Esta clase manejará las solicitudes HTTP y devolverá las respuestas HTTP adecuadas.
@RestController
// Se asigna una ruta base "/taxi" para todas las solicitudes manejadas por este controlador.
@RequestMapping("/taxi")
public class TaxiController {
    // Se define un constructor que toma un objeto TaxiService como parámetro e inicializa el campo taxiServices.
    private final TaxiService taxiServices;
    public TaxiController (TaxiService taxiService) {
        this.taxiServices = taxiService;
    }

    // Se documenta el método getAllTaxis() con Swagger utilizando la anotación @Operation para resumir la operación del método y @ApiResponses para describir las respuestas posibles.
    @Operation(summary = "Get-All-Taxis")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the taxi",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Taxi.class)) }),
            @ApiResponse(responseCode = "400", description = "Object Taxi invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Taxi not found",
                    content = @Content)
    })

    // Maneja las solicitudes GET en la ruta "/taxi/all" para obtener todos los taxis.
    // El método acepta dos parámetros opcionales: "page" para el número de página y "size" para el tamaño de la página.
    @GetMapping("/all")
    public Page<Taxi> getAllTaxis( @Parameter(description = "Page number, default is 0") @RequestParam(defaultValue = "0") int page,
                                   @Parameter(description = "Number of items per page, default is 10") @RequestParam(defaultValue = "10") int size) {
        // Llama al método getAllTaxis(pageable) del servicio TaxiService para obtener una página de resultados de taxis.
        // Se crea un objeto Pageable utilizando los parámetros de página y tamaño recibidos.
        Pageable pageable = PageRequest.of(page, size);
        return taxiServices.getAllTaxis(pageable);
    }
}
