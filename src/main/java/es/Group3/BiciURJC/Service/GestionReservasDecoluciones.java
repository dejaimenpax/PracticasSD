package es.Group3.BiciURJC.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.Group3.BiciURJC.Repository.BicicletasRepository;
import es.Group3.BiciURJC.Repository.EstacionRepository;
import es.Group3.BiciURJC.model.Bicicleta;
import es.Group3.BiciURJC.model.Estacion;
import es.Group3.BiciURJC.model.EstadoBicicleta;

@Service
public class GestionReservasDecoluciones {
	private EstacionRepository estacionrepo;
	private BicicletasRepository bicicletarepo;
	
	
	//Progreso Parametros estacion
	
	public void setEstacion (long id, Estacion estacion) {
		bicicletarepo.updateEstacionById(id, estacion);
	}
	
	public Optional<Estacion> findByIdEstacion (long id) {
		return estacionrepo.findById(id);
	}
	
	public List <Estacion> findAllEstacion() {
		return estacionrepo.findAll();
	}
	
	public void deleteEstacion (Long id) {
		estacionrepo.deleteById(id);
	}
	
	public Estacion saveEstacion (Estacion estacion) {
		Estacion nuevaEstacion =estacionrepo.save(estacion);
		return nuevaEstacion;
	}
	
	// Progreso Parametros Bicicleta
	
	public Optional<Bicicleta> findByIdBici (long id) {
		return bicicletarepo.findById(id);
	}
	
	public List <Bicicleta> findAllBici() {
		return bicicletarepo.findAll();
	}
	
	public Bicicleta saveBici (Bicicleta bk) {
		Bicicleta nuevaBici =bicicletarepo.save(bk);
		return nuevaBici;
	}
	
	public void deleteByIdBici (Long id) {
		bicicletarepo.deleteById(id);
	}
	
	public void editStateBici (Long id, EstadoBicicleta estado) {
		bicicletarepo.updateEstadoById(id, estado);
	}
	
	
}
