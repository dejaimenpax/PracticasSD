package es.Group3.BiciURJC.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String password;
    private String fullName;
    private String entryDate;
    private EstadoUsuario state;
    //private BufferedImage BufferedImage photo;

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public EstadoUsuario getState() {
        return state;
    }

    public void setState(EstadoUsuario state) {
        this.state = state;
    }

    protected Usuario() {}

    public Usuario(String password, String fullName){
        super();
        this.password = password;
        this.fullName = fullName;
        this.entryDate = dtf.format(LocalDate.now());
        this.state = EstadoUsuario.ACTIVO;
    }

    @Override
    public String toString(){
        return("");
    }

}
