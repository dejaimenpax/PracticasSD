package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Estaciones {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String idE;
	
	private int num_serieE;
	private String insDate;
	private int size;
	private double lon; //coordenada longitud
	private double lat; //coordenada latitud
	private boolean stateE; //true=activa, false=inactiva
	
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	
	protected Estaciones() {};
	
	public Estaciones(int num_serieE, int size, int lon, int lat, boolean stateE) { //deberiamos meter una excepcion para que size sea 5 o 10?
		this.num_serieE = num_serieE;												//y el id se supone que lo asigna el sistema automaticamente
		this.insDate = dtf.format(LocalDate.now());
		this.size = size;
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
	
	 @Override
	    public String toString(){
	        return("Estación " + num_serieE + ", id: "+ idE + ", fecha: " + insDate +
	        		", capacidad: " + size + ", longitud: " + lon + ", latitud" + lat +
	        		", estado: " + stateE);
	    }

}
