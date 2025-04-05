package entities;
import java.util.*;

public class Salon {
    private String locatie;
    private int capacitateMaxima;
    private List<Pacient> pacientiInternati;

    public Salon(List<Pacient> pacientiInternati, String locatie, int capacitateMaxima) {
        this.pacientiInternati = pacientiInternati;
        this.locatie = locatie;
        this.capacitateMaxima = capacitateMaxima;
    }

    public Salon()
    {
        this.pacientiInternati = new ArrayList<>();
        this.locatie = "";
        this.capacitateMaxima = 0;
    }

    public List<Pacient> getPacientiInternati() {
        return pacientiInternati;
    }

    public void setPacientiInternati(List<Pacient> pacientiInternati) {
        this.pacientiInternati = pacientiInternati;
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
}
