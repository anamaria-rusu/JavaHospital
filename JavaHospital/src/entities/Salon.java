package entities;
import java.util.*;

public class Salon {
    private final int idSalon;
    private String locatie;
    private int capacitateMaxima;

    public Salon(int idSalon,String locatie, int capacitateMaxima) {
        this.idSalon=idSalon;
        this.locatie = locatie;
        this.capacitateMaxima = capacitateMaxima;
    }


    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public int getCapacitateMaxima() {
        return capacitateMaxima;
    }

    public void setCapacitateMaxima(int capacitateMaxima) {
        this.capacitateMaxima = capacitateMaxima;
    }

    public int getId() {
        return idSalon;
    }

}
