package es.Group3.BiciURJC.model;

import es.Group3.BiciURJC.exceptions.IllegalStateChange;
import es.Group3.BiciURJC.exceptions.IllegalStationAssociation;
import es.Group3.BiciURJC.exceptions.IncorrectStationCapacity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Bicicleta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 16, unique = true)
    private String num_serie;
    private String modelo;
    private String entryDate;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");

    private LinkedList<EstadoBicicleta> estado = new LinkedList<>();

    @ManyToOne
    private Estacion estacion;

    protected Bicicleta() {}

    public Bicicleta(String num_serie, String modelo, EstadoBicicleta estado){
        super();
        this.num_serie = num_serie;
        this.modelo = modelo;
        this.entryDate = dtf.format(LocalDate.now());
        this.estado.addFirst(estado);
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

    public LinkedList<EstadoBicicleta> getEstado() {
        return estado;
    }

    public void setEstado(LinkedList<EstadoBicicleta> estado) {
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
        return "Bicicleta{" +
                "id=" + id +
                ", num_serie='" + num_serie + '\'' +
                ", modelo='" + modelo + '\'' +
                ", entryDate='" + entryDate + '\'' +
                ", estado=" + estado.element() +
                ", estacion=" + estacion +
                '}';
    }
}
