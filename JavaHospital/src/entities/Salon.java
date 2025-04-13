package entities;
import java.util.*;

public class Salon {
    private static int nrSaloane = 100;
    private final int idSalon;
    private String locatie;
    private int capacitateMaxima;
    private int capacitateCurenta;


    public Salon(String locatie, int capacitateMaxima) {
        this.idSalon = ++ nrSaloane;
        this.locatie = locatie;
        this.capacitateMaxima = capacitateMaxima;
        this.capacitateCurenta = 0;
    }

    public Salon()
    {
        this.idSalon = ++ nrSaloane;
        this.locatie = "";
        this.capacitateMaxima = 0;
        this.capacitateCurenta = 0;
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

    public int getCapacitateCurenta() {
        return capacitateCurenta;
    }

    public void setCapacitateCurenta(int capacitateCurenta) {
        this.capacitateCurenta = capacitateCurenta;
    }

    public int getIdSalon() {
        return idSalon;
    }
}
