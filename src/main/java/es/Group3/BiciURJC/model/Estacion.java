package es.Group3.BiciURJC.model;

import es.Group3.BiciURJC.Service.CicloVidaBicicletas;
import es.Group3.BiciURJC.exceptions.IllegalStateChange;
import es.Group3.BiciURJC.exceptions.IllegalStationAssociation;
import es.Group3.BiciURJC.exceptions.IncorrectStationCapacity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;

@Entity
public class Estacion {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String idE;
	
	private int num_serieE;
	private String insDate;
	private int size;
	private double lon; //coordenada longitud
	private double lat; //coordenada latitud
	private boolean stateE; //true=activa, false=inactiva
	private Hashtable<String, Bicicleta> listaBicis;
	
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	
	protected Estacion() {};
	
	public Estacion(int num_serieE, int size, int lon, int lat, boolean stateE) throws IncorrectStationCapacity {
		super();
		this.num_serieE = num_serieE;												//y el id se supone que lo asigna el sistema automaticamente
		this.insDate = dtf.format(LocalDate.now());
		if (size!=5 && size!=10){
			throw new IncorrectStationCapacity("Station capacity should be 5 or 10. Given: " + size);
		}
		this.lon = lon;
		this.lat = lat;
		this.stateE = stateE;
	}

	public int getNum_serieE() {
		return num_serieE;
	}

	public void setNum_serieE(int num_serieE) {
		this.num_serieE = num_serieE;
	}

	public String getInsDate() {
		return insDate;
	}

	public void setInsDate(String insDate) {
		this.insDate = insDate;
	}

	public boolean isStateE() {
		return stateE;
	}

	public void setStateE(boolean stateE) {
		this.stateE = stateE;
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

	public String getIdE() {return this.idE;}

	public Hashtable<String, Bicicleta> getListaBicis() {return this.listaBicis;}

	
	 @Override
	    public String toString(){
	        return("Estación " + num_serieE + ", id: "+ idE + ", fecha: " + insDate +
	        		", capacidad: " + size + ", longitud: " + lon + ", latitud" + lat +
	        		", estado: " + stateE);
	    }

}
