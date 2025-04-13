package services.services;

import entities.Internare;
import entities.Salon;

import java.util.ArrayList;
import java.util.List;

// Servicii (functionalitati) pentru entitatea Salon
public class SalonServices
{
    // Lista saloanelor din spital
    private List<Salon> saloane = new ArrayList<>();

    public SalonServices()
    {
        // Se considera ca saloanele sunt locatii fizice, preexistente
        Salon salon;
        salon = new Salon("Etaj 1", 3); saloane.add(salon);
        salon = new Salon("Etaj 1", 5); saloane.add(salon);
        salon = new Salon("Etaj 2", 6); saloane.add(salon);
        salon = new Salon("Etaj 2", 2); saloane.add(salon);

    }

    // preluarea listei de saloane
    public List<Salon> getSaloane() {
        return new ArrayList<>(saloane);
    }

    // se verifica cel mai bun salon disponibil pentru internarea unui pacient
    public Salon verificaDisponibilitate()
    {
        if (saloane == null || saloane.isEmpty()) {
            return null;
        }

        Salon salonCuCeiMaiPutiniPacienti = saloane.get(0); // Începem cu primul salon
        for (Salon salon : saloane) {
            if (salon.getCapacitateCurenta() < salonCuCeiMaiPutiniPacienti.getCapacitateCurenta() && salon.getCapacitateCurenta()<salon.getCapacitateMaxima()) {
                salonCuCeiMaiPutiniPacienti = salon;
            }
        }
        return salonCuCeiMaiPutiniPacienti;
    }


}
