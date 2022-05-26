package es.Group3.BiciURJC.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Bicicleta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private EstadoBicicleta estado1;
    @Column(length = 16, unique = true)
    private String num_serie;
    private String modelo;
    private String entryDate;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    @Column(length = 1024)
    private CapsulaEstado<EstadoBicicleta> estado = new CapsulaEstado<>();
    private double pricebook;

    @JsonIgnore
    @ManyToOne
    private Estacion estacion;

    protected Bicicleta() {}

    public Bicicleta(String num_serie, String modelo, EstadoBicicleta estado, double pricebook){
        super();
        this.num_serie = num_serie;
        this.modelo = modelo;
        this.entryDate = dtf.format(LocalDate.now());
        this.estado.getLista().addFirst(estado);
        this.pricebook = pricebook;
    }

    public Bicicleta(String num_serie, String modelo, EstadoBicicleta estado, Estacion estacion, double pricebook){
        super();
        this.num_serie = num_serie;
        this.modelo = modelo;
        this.entryDate = dtf.format(LocalDate.now());
        this.estado.getLista().addFirst(estado);
        this.estacion = estacion;
        this.pricebook = pricebook;
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

    public CapsulaEstado<EstadoBicicleta> getEstado() {
        return estado;
    }

    public void setEstado(CapsulaEstado<EstadoBicicleta> estado) {
        this.estado = estado;
    }

    public Estacion getEstacion() {
        return estacion;
    }

    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }

    public double getPricebook() {
        return pricebook;
    }

    public void setPricebook(double pricebook) {
        this.pricebook = pricebook;
    }

    @Override
    public String toString() {
        return "Bicicleta{" +
                "id=" + id +
                ", num_serie='" + num_serie + '\'' +
                ", modelo='" + modelo + '\'' +
                ", entryDate='" + entryDate + '\'' +
                ", estado=" + estado.getLista().element() +
                ", estacion=" + estacion +
                '}';
    }
}
