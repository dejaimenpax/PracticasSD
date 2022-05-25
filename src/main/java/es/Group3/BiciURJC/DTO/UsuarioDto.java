package es.Group3.BiciURJC.DTO;

import es.Group3.BiciURJC.model.EstadoUsuario;

import java.io.Serializable;
import java.util.Objects;

public class UsuarioDto implements Serializable {
    private final String login;
    private final String fullName;
    private final String entryDate;
    private final EstadoUsuario estado;
    private final double saldo;

    public UsuarioDto(String login, String fullName, String entryDate, EstadoUsuario estado, double saldo) {
        this.login = login;
        this.fullName = fullName;
        this.entryDate = entryDate;
        this.estado = estado;
        this.saldo = saldo;
    }

    public String getLogin() {
        return login;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioDto entity = (UsuarioDto) o;
        return Objects.equals(this.login, entity.login) &&
                Objects.equals(this.fullName, entity.fullName) &&
                Objects.equals(this.entryDate, entity.entryDate) &&
                Objects.equals(this.estado, entity.estado) &&
                Objects.equals(this.saldo, entity.saldo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, fullName, entryDate, estado, saldo);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "login = " + login + ", " +
                "fullName = " + fullName + ", " +
                "entryDate = " + entryDate + ", " +
                "estado = " + estado + ", " +
                "saldo = " + saldo + ")";
    }
}
