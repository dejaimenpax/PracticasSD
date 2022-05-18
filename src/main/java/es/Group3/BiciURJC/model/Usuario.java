package es.Group3.BiciURJC.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String password;
    @Column(unique = true)
    private String login;
    private String fullName;
    private String entryDate;
    private EstadoUsuario estado;
    private double saldo;

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public EstadoUsuario getEstado() {
        return this.estado;
    }

    public void setEstado(EstadoUsuario estado) {
        this.estado = estado;
    }

    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo=saldo;
    }


    protected Usuario() {}

    public Usuario(String password, String fullName, String login, double saldo){
        super();
        this.password = password;
        this.fullName = fullName;
        this.entryDate = dtf.format(LocalDate.now());
        this.estado = EstadoUsuario.ACTIVO;
        this.login = login;
        this.saldo = saldo;
    }

    @Override
    public String toString(){
        return("");
    }

}
