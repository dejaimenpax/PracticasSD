package es.Group3.BiciURJC.model;

import javax.annotation.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Rest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private long idestacion;
	private long idbicicleta;
	private long idusuario;
	
	public Rest(long idestacion,long idbicicleta,long idusuario) {
		this.idbicicleta=idbicicleta;
		this.idestacion=idestacion;
		this.idusuario=idusuario;
	}
	
	public long getIdestacion() {
		return idestacion;
	}
	public void setIdestacion(long idestacion) {
		this.idestacion = idestacion;
	}
	public long getIdbicicleta() {
		return idbicicleta;
	}
	public void setIdbicicleta(long idbicicleta) {
		this.idbicicleta = idbicicleta;
	}
	public long getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(long idusuario) {
		this.idusuario = idusuario;
	}
	
}
