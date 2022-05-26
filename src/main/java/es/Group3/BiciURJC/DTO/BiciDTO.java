package es.Group3.BiciURJC.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.Group3.BiciURJC.model.CapsulaEstado;
import es.Group3.BiciURJC.model.Estacion;
import es.Group3.BiciURJC.model.EstadoBicicleta;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
public class BiciDTO {

    private EstadoBicicleta estado1;
    @Column(length = 16, unique = true)
    private String num_serie;
    private String modelo;
    private String entryDate;
    @Column(length = 1024)
    private CapsulaEstado<EstadoBicicleta> estado = new CapsulaEstado<>();
    private double pricebook;

    @JsonIgnore
    @ManyToOne
    private Estacion estacion;

    protected BiciDTO() {}

    public BiciDTO(String num_serie, String modelo, String entryDate, EstadoBicicleta estado, Estacion estacion, double pricebook){
        super();
        this.num_serie = num_serie;
        this.modelo = modelo;
        this.entryDate = entryDate;
        this.estado.getLista().addFirst(estado);
        this.estacion = estacion;
        this.pricebook = pricebook;
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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiciDTO entity = (BiciDTO) o;
        return Objects.equals(this.num_serie, entity.num_serie) &&
                Objects.equals(this.modelo, entity.modelo) &&
                Objects.equals(this.entryDate, entity.entryDate) &&
                Objects.equals(this.estado, entity.estado) &&
                Objects.equals(this.estacion, entity.estacion) &&
                Objects.equals(this.pricebook, entity.pricebook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num_serie, modelo, entryDate, estado, estacion, pricebook);
    }

    @Override
    public String toString() {
        return "Bicicleta{" +
                ", num_serie='" + num_serie + '\'' +
                ", modelo='" + modelo + '\'' +
                ", entryDate='" + entryDate + '\'' +
                ", estado=" + estado.getLista().element() +
                ", estacion=" + estacion +
                '}';
    }
}

