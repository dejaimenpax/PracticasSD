package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import net.bytebuddy.implementation.bind.annotation.This;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String password;
    private String fullName;
    private String entryDate;
    private boolean state;
    //private BufferedImage BufferedImage photo;

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");

    protected Usuario() {}

    public String getId() {
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

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Usuario(String id, String password, String fullName){
        this.id = id;
        this.password = password;
        this.fullName = fullName;
        this.entryDate = dtf.format(LocalDate.now());
        state = true;
    }

    @Override
    public String toString(){
    	if (this.state=true) {
           return "Estado Activo" + "\n" + "Id= " + id + "\n" + "Nombre: " + fullName + "\n" + "Fecha de entrada: " + entryDate + '\n'; 
        } else {
           return "Estado Inactivo" + "\n" + "Id= " + id + "\n" + "Nombre: " + fullName + "\n" + "Fecha de entrada: " + entryDate + '\n';
        }	
    }
    
    public int compareTo(Usuario o) {
        return this.id.compareTo(o.id);}

}
