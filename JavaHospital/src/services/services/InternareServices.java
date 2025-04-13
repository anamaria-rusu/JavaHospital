package services.services;
import entities.*;

import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.*;


public class InternareServices
{
    private List<Internare> internari = new ArrayList<>();

    public Internare internarePacient(Pacient pacient, Salon salon, String diagnostic, String departament) {
        Internare internareNoua = new Internare(pacient,salon,diagnostic,departament);
        internari.add(internareNoua);
        pacient.adaugaIstoric(internareNoua);
        return internareNoua;
    }

    public List<Internare> getPacientiInternati(Salon salon)
    {
        List<Internare> pacientiInternati = new ArrayList<>();
        for (Internare internare : internari){
            if(internare.getSalon().getIdSalon() == salon.getIdSalon())
                pacientiInternati.add(internare);
        }
        return pacientiInternati;
    }
}
