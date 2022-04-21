package es.Group3.BiciURJC.model;

import java.io.Serializable;

public class Coords implements Serializable {
    private int grados;
    private int mins;
    private int secs;

    public Coords(int grados, int mins, int secs){
        this.grados = grados;
        this.mins = mins;
        this.secs = secs;
    }

    public int getGrados() {
        return grados;
    }

    public void setGrados(int grados) {
        this.grados = grados;
    }

    public int getMins() {
        return mins;
    }

    public void setMins(int mins) {
        this.mins = mins;
    }

    public int getSecs() {
        return secs;
    }

    public void setSecs(int secs) {
        this.secs = secs;
    }
    @Override
    public String toString(){
        return(grados + "º " + mins + "' " + secs + "''");
    }

}
