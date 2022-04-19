package es.Group3.BiciURJC.model;

import es.Group3.BiciURJC.exceptions.IllegalStateChange;
import es.Group3.BiciURJC.exceptions.IllegalStationAssociation;
import es.Group3.BiciURJC.exceptions.IncorrectStationCapacity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Bicicleta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String num_serie;
    private String modelo;
    private String entryDate;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");

    private EstadoBicicleta estado;

    @ManyToOne
    private Estacion estacion;

    protected Bicicleta() {}

    public Bicicleta(String num_serie, String modelo, EstadoBicicleta estado){
        super();
        this.num_serie = num_serie;
        this.modelo = modelo;
        this.entryDate = dtf.format(LocalDate.now());
        this.estado = estado;
    }

    public Long getId() {
        return id;
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
        return entryDate;
    }

    public void setFecha(String entryDate) {
        this.entryDate = entryDate;
    }

    public EstadoBicicleta getEstado() {
        return estado;
    }

    public void setEstado(EstadoBicicleta estado) {
        this.estado = estado;
    }

    public Estacion getEstacion() {
        return estacion;
    }

    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }

    @Override
    public String toString() {
        return "CicloVidaBicicletas{" +
                "num_serie='" + num_serie + '\'' +
                ", modelo='" + modelo + '\'' +
                ", fecha='" + entryDate + '\'' +
                '}';
    }

}
