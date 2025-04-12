package services.services;

import entities.Internare;
import entities.Medic;
import entities.Pacient;
import entities.Salon;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class SalonServices {

    private List<Salon> saloane = new ArrayList<>();

    public SalonServices()
    {
        Salon salon2 = new Salon("etaj 2", 3);
        saloane.add(salon2);
    }

    public List<Salon> getSaloane() {
        return new ArrayList<>(saloane);
    }

    public Salon verificaDisponibilitate(){
        if (saloane == null || saloane.isEmpty()) {
            return null; // Sau arunca o excepție, dacă vrei
        }

        Salon salonCuCeiMaiPutiniPacienti = saloane.get(0); // Începem cu primul salon
        for (Salon salon : saloane) {
            if (salon.getCapacitateCurenta() < salonCuCeiMaiPutiniPacienti.getCapacitateCurenta() && salon.getCapacitateCurenta()<salon.getCapacitateMaxima()) {
                salonCuCeiMaiPutiniPacienti = salon;
            }
        }
        return salonCuCeiMaiPutiniPacienti;
    }

    public void internarePacient(Internare internare, Salon salon) {
        Salon salonGasit = null;
        for (Salon s : saloane) {
            if (s.getIdSalon() == salon.getIdSalon()) {
                salonGasit = s;
                break;
            }
        }
        if (salonGasit != null) {
            salonGasit.getPacientiInternati().add(internare);
        }
    }

}
