package es.Group3.BiciURJC.controller;


import es.Group3.BiciURJC.Service.BicicletaService;
import es.Group3.BiciURJC.Service.EstacionService;
import es.Group3.BiciURJC.Service.GestionReservasDecoluciones;
import es.Group3.BiciURJC.controller.exceptions.IncorrectStationCapacity;
import es.Group3.BiciURJC.controller.exceptions.InsufficentBalance;
import es.Group3.BiciURJC.model.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/reserves")
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

	@PutMapping("/bicycles")
	@Operation(summary = "Reservar bicicleta")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Bicicleta reservada",
					content = {@Content(
							mediaType = "application/json",
							schema = @Schema(implementation=Estacion.class)
					)}
			),
			@ApiResponse(
					responseCode = "400",
					description = "Esta bicicleta no se puede reservar",
					content = @Content
			),
			@ApiResponse(
					responseCode = "404",
					description = "Recursos no encontrados",
					content = @Content
			)
	})
	public ResponseEntity<Bicicleta> reserve(@RequestBody CapsulaIds identificadores) {
		Optional<Estacion> aux_st = estaciones.findOne(identificadores.getStation_id());
		Optional<Bicicleta> aux_bici = bicicletas.findOne(identificadores.getBicycle_id());

		if (aux_st.isPresent() && aux_bici.isPresent()) {

			Estacion estacion = aux_st.get();
			Bicicleta bici = aux_bici.get();
			RestTemplate plantilla = new RestTemplate();
			String url ="http://localhost:8081/users/" + identificadores.getUser_id();
			ObjectNode data = plantilla.getForObject(url, ObjectNode.class);
			assert data != null;
			double saldo = data.get("saldo").asDouble(0);

			if(saldo >=3) {
				if(estacion.getEstado() == EstadoEstacion.ACTIVA && bici.getEstacion().getId().equals(estacion.getId()) && bici.getEstado().getLista().getFirst() == EstadoBicicleta.EN_BASE){
					double new_saldo = data.get("saldo").asDouble()-3;
					aux_bici.get().getEstado().getLista().removeFirst();
					aux_bici.get().getEstado().getLista().addFirst(EstadoBicicleta.RESERVADA);
					aux_bici.get().setEstacion(null);
					aux_st.get().deleteBike(aux_bici.get());
					estaciones.save(aux_st.get());
					bicicletas.save(aux_bici.get());
					data.put("saldo", new_saldo);
					plantilla.put(url,data);
					URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
					return ResponseEntity.created(location).build();
				}
				else
					return ResponseEntity.unprocessableEntity().build();
			} else
				throw new InsufficentBalance("Saldo insuficiente");
		} else
			return ResponseEntity.notFound().build();
	}

	@PutMapping("/bicycles/free")
	@Operation(summary = "Devolver bicicleta")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Bicicleta devuelta a una estación",
					content = {@Content(
							mediaType = "application/json",
							schema = @Schema(implementation=Estacion.class)
					)}
			),
			@ApiResponse(
					responseCode = "400",
					description = "Esta bicicleta no se puede devolver a la estación especificada",
					content = @Content
			),
			@ApiResponse(
					responseCode = "404",
					description = "Recursos no encontrados",
					content = @Content
			)
	})
	public ResponseEntity<Bicicleta> returnBicycle(@RequestBody CapsulaIds identificadores){
		Optional<Estacion> aux_st = estaciones.findOne(identificadores.getStation_id());
		Optional<Bicicleta> aux_bici = bicicletas.findOne(identificadores.getBicycle_id());
		if (aux_st.isPresent() && aux_bici.isPresent()) {
			Estacion estacion= aux_st.get();
			Bicicleta bici= aux_bici.get();
			if(estacion.getEstado() == EstadoEstacion.ACTIVA && bici.getEstado().getLista().getFirst() == EstadoBicicleta.RESERVADA) {
				if(estacion.getSize()>estacion.getListaBicis().size()) {
					RestTemplate plantilla = new RestTemplate();
					String url = "http://localhost:8081/users/" + identificadores.getUser_id();
					ObjectNode data = plantilla.getForObject(url, ObjectNode.class);
					assert data != null;
					double new_saldo = data.get("saldo").asDouble()+ 1.5;
					aux_bici.get().getEstado().getLista().removeFirst();
					aux_bici.get().getEstado().getLista().addFirst(EstadoBicicleta.EN_BASE);
					aux_bici.get().setEstacion(aux_st.get());
					aux_st.get().addBici(aux_bici.get());
					estaciones.save(aux_st.get());
					bicicletas.save(aux_bici.get());
					data.put("saldo", new_saldo);
					plantilla.put(url,data);
					URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
					return ResponseEntity.created(location).build();
				}
				else
					throw new IncorrectStationCapacity("La estación no admite más bicicletas");
			}
			else {
				return ResponseEntity.unprocessableEntity().build();
			}
		}
		else
			return ResponseEntity.notFound().build();

	}



}
