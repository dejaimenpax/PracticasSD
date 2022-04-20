package es.Group3.BiciURJC.model;

import es.Group3.BiciURJC.Service.CicloVidaBicicletas;
import es.Group3.BiciURJC.controller.exceptions.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@Entity
public class Estacion {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	@Column(length = 16, unique = true)
	private String num_serie;
	private String entryDate;
	private int size;
	private double lon; //coordenada longitud
	private double lat; //coordenada latitud
	private EstadoEstacion state;

	@SuppressWarnings("JpaAttributeTypeInspection")
	@OneToMany(mappedBy = "estacion", orphanRemoval = true)
	private List<Bicicleta> listaBicis = new ArrayList<>();
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	
	protected Estacion() {}
	
	public Estacion(String num_serie, int size, int lon, int lat, EstadoEstacion state) throws IncorrectStationCapacity {
		super();
		this.num_serie = num_serie;//y el id se supone que lo asigna el sistema automaticamente
		this.entryDate = dtf.format(LocalDate.now());
		if (size!=5 && size!=10){
			throw new IncorrectStationCapacity("Station capacity should be 5 or 10. Given: " + size);
		}
		this.lon = lon;
		this.lat = lat;
		this.state = state;
		this.size = size;
	}

	/*public Estacion(String num_serie, int size, int lon, int lat, boolean state) throws IncorrectStationCapacity {
		super();
		this.num_serie = num_serie;//y el id se supone que lo asigna el sistema automaticamente
		this.entryDate = dtf.format(LocalDate.now());
		if (size!=5 && size!=10){
			throw new IncorrectStationCapacity("Station capacity should be 5 or 10. Given: " + size);
		}
		this.lon = lon;
		this.lat = lat;
		if(state){
			this.state=EstadoEstacion.ACTIVA;
		}else{
			this.state=EstadoEstacion.INACTIVA;
		}
		this.size = size;
	}*/

	public String getNum_serie() {
		return num_serie;
	}

	public void setNum_serie(String num_serie) {
		this.num_serie = num_serie;
	}

	public String getentryDate() {
		return entryDate;
	}

	public void setentryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public EstadoEstacion isState() {
		return state;
	}

	public void setState(EstadoEstacion state) {
		this.state = state;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public double getLon() {
		return lon;
	}
	
	public void setLon(double lon) {
		this.lon = lon;
	}
	
	public double getLat() {
		return lat;
	}
	
	public void setLat(double lat) {
		this.lat = lat;
	}

	public Long getId() {return this.id;}

	public List<Bicicleta> getListaBicis() {return this.listaBicis;}

	
	 @Override
	    public String toString(){
	        return("Estación " + num_serie + ", id: "+ id + ", fecha: " + entryDate +
	        		", capacidad: " + size + ", longitud: " + lon + ", latitud" + lat +
	        		", estado: " + state);
	    }

}
