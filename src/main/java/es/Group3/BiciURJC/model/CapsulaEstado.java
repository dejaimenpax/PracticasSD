package es.Group3.BiciURJC.model;


import java.io.Serializable;
import java.util.LinkedList;

public class CapsulaEstado<T> implements Serializable {

    private LinkedList<T> lista;

    public LinkedList<T> getLista() {
        return lista;
    }

    public void setLista(LinkedList<T> lista) {
        this.lista = lista;
    }

    public CapsulaEstado(){
        lista = new LinkedList<>();
    }

    @Override
    public String toString(){
        return lista.toString().substring(1,lista.toString().length()-1);
    }
}

