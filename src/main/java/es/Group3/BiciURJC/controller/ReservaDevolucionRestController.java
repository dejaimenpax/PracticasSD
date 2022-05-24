package es.Group3.BiciURJC.controller;


import es.Group3.BiciURJC.Service.BicicletaService;
import es.Group3.BiciURJC.Service.EstacionService;
import es.Group3.BiciURJC.Service.GestionReservasDecoluciones;
import es.Group3.BiciURJC.model.Bicicleta;
import es.Group3.BiciURJC.model.Estacion;
import es.Group3.BiciURJC.model.Rest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/reserve")
public class ReservaDevolucionRestController {
	@Autowired
	private BicicletaService bicicletas;

	@Autowired
	private EstacionService estaciones;



	@GetMapping("/stations")
	@Operation(summary = "Obtener lista de estaciones")
	public Collection<Estacion> getStations() {
		return estaciones.findAll();
	}

	@GetMapping("/bicycles")
	@Operation(summary = "Obtener lista de bicicletas")
	public Collection<Bicicleta> getBicycles() {
		return bicicletas.findAll();
	}


}
