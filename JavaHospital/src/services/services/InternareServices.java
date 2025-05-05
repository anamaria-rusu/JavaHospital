package services.services;
import entities.*;

import java.util.ArrayList;
import java.util.List;


public class InternareServices
{
    private List<Internare> internari = new ArrayList<>();

    public Internare internarePacient(Pacient pacient, Salon salon, String diagnostic, String departament) {
        Internare internareNoua = new Internare(pacient,salon,diagnostic,departament);
        internari.add(internareNoua);
        pacient.adaugaIstoric(internareNoua);
        salon.setCapacitateCurenta(salon.getCapacitateCurenta()+1);
        return internareNoua;
    }

    public List<Internare> getPacientiInternati(Salon salon)
    {
        List<Internare> pacientiInternati = new ArrayList<>();
        for (Internare internare : internari){
            if(internare.getSalon().getIdSalon() == salon.getIdSalon()) {
                pacientiInternati.add(internare);
            }
        }
        return pacientiInternati;
    }
}
