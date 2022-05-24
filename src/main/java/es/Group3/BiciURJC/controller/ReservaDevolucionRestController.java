package es.Group3.BiciURJC.controller;


import es.Group3.BiciURJC.Service.GestionReservasDecoluciones;
import es.Group3.BiciURJC.model.Bicicleta;
import es.Group3.BiciURJC.model.Estacion;
import es.Group3.BiciURJC.model.Rest;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/reserve")
public class ReservaDevolucionRestController  {
	
	private GestionReservasDecoluciones reservasDevoluciones;
	
	//Reservas
	
	@PutMapping("/")
	public ResponseEntity<Bicicleta> reservar(@RequestBody Rest ids) {
		try {
		Optional<Estacion> estacion =reservasDevoluciones.findByIdEstacion(ids.getIdestacion());
		Optional<Bicicleta> bici =reservasDevoluciones.findByIdBici(ids.getIdbicicleta());
		if(estacion!=null&&bici!=null) {
			Estacion estacionNueva=estacion.get();
			Bicicleta biciNueva=bici.get();
			RestTemplate restTemplate = new RestTemplate();
			String url ="http://localhost:8081/api/users/"+ids.getIdusuario();
			ObjectNode data = restTemplate.getForObject(url, ObjectNode.class);
			double saldoActual =data.get("saldo").asDouble();
			if(saldoActual>=3) {
				if(estacionNueva.getEstado().equals("ACTIVO")&&biciNueva.getEstacion().getId()==estacionNueva.getId()&&biciNueva.getEstado().equals("En-Base")) {
					Double NuevoSaldo =data.get("saldo").asDouble()-3;
				
					//bici.get().setEstado(CapsulaEstado<RESERVADA>);
					bici.get().setEstacion(null);
					estacion.get().deleteBike(bici.get());
					reservasDevoluciones.saveEstacion(estacion.get());
					reservasDevoluciones.saveBici(bici.get());
					data.put("saldo",NuevoSaldo );
					restTemplate.put(url,data);
					
					URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
					return ResponseEntity.created(location).build();
				}
				else
					return ResponseEntity.unprocessableEntity().build();
			}else
				System.out.println("\n no hay saldo suficiente");
		}else
			return ResponseEntity.notFound().build();
		}catch(NullPointerException ex) {System.out.println("\nNo se ha podido encontrar esa bici en esa estacion o bien los ids son nulos");}
		return null; 
	}
	
	@PutMapping("/libre")
	public ResponseEntity<Bicicleta> liberar(@RequestBody Rest ids){
		Optional<Estacion> estacion =reservasDevoluciones.findByIdEstacion(ids.getIdestacion());
		Optional<Bicicleta> bici =reservasDevoluciones.findByIdBici(ids.getIdbicicleta());
		if(estacion!=null&&bici!=null) {
			Estacion estacionNueva=estacion.get();
			Bicicleta biciNueva=bici.get();
			if(estacionNueva.getEstado().equals("ACTIVO")&&biciNueva.getEstado().equals("RESERVADA")) {
				if(estacionNueva.getSize()>estacionNueva.getListaBicis().size()) {
		
					RestTemplate restTemplate = new RestTemplate();
					String url ="http://localhost:8081/api/users/"+ids.getIdusuario();
					ObjectNode data = restTemplate.getForObject(url, ObjectNode.class);
					Double NuevoSaldo =data.get("saldo").asDouble()+1.5;
					//bici.get().setEstado(CapsulaEstado<EstadoBicicleta.EN_BASE>);
					bici.get().setEstacion(estacion.get());
					estacion.get().addBici(bici.get());
					reservasDevoluciones.saveEstacion(estacion.get());
					reservasDevoluciones.saveBici(bici.get());
					data.put("saldo",NuevoSaldo );
					restTemplate.put(url,data);
					
					URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
						return ResponseEntity.created(location).build();
					}
				else
					throw new NullPointerException("La capacidad esta llena");
				}
			else {
				return ResponseEntity.unprocessableEntity().build();
			}
		}
		else
			return ResponseEntity.notFound().build();
	}

	
	@GetMapping("/bicis")
	public List<Bicicleta> getBicis(){
		
		return reservasDevoluciones.findAllBici();
	}
	
	@GetMapping("/estaciones")
	public List<Estacion> getEstaciones(){
		
		return reservasDevoluciones.findAllEstacion();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Bicicleta> getUsuario(@PathVariable long id){
		
		Optional<Bicicleta> usuario= reservasDevoluciones.findByIdBici(id);
		if (usuario != null) 
			return ResponseEntity.ok(usuario.get());
		else
			return ResponseEntity.notFound().build();
	}
	
}