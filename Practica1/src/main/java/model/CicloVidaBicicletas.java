package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CicloVidaBicicletas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String num_serie;

    private String modelo;
    private String fecha;
    private enum estado{sin_base,en_base,baja};

    protected CicloVidaBicicletas() {}

    public CicloVidaBicicletas(String modelo, String fecha) {
        this.modelo = modelo;
        this.fecha = fecha;
    }

    public String getNum_serie() {
        return num_serie;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "CicloVidaBicicletas{" +
                "num_serie='" + num_serie + '\'' +
                ", modelo='" + modelo + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
