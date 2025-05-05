package services.services;

import entities.Medic;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MediciServices implements PersoanaServices<Medic>{

    private List<Medic> medici = new ArrayList<>();
    private LevenshteinDistance motorCautare = new LevenshteinDistance();


    public MediciServices() {
        // adaugam initial un medic pentru test
        Medic medic = new Medic("Andrei", "Ionescu", LocalDate.of(1975, 10, 25), "andrei.ionescu@email.com", "0745123456", LocalDate.of(2005, 6, 10), "Cardiologie");
        medici.add(medic);
    }


    public void adaugaMedic(String nume, String prenume, LocalDate dataNasterii, String email, String telefon, LocalDate dataAngajarii, String departament) {
        medici.add(new Medic(nume,prenume,dataNasterii,telefon,email,dataAngajarii,departament));
    }

    public List<Medic> getPersoane() {
        return new ArrayList<>(medici);
    }


    public List<Medic> cautaPersoane(String nume, String prenume){
        List<Medic> mediciCautati = new ArrayList<>();
        int distantaLevenshteinNume = 0;
        int distantaLevenshteinPrenume = 0;

        for(Medic medic : medici){

            if(!nume.isEmpty())
                distantaLevenshteinNume =  motorCautare.apply(nume,medic.getNume());
            if(!prenume.isEmpty())
                distantaLevenshteinPrenume =  motorCautare.apply(prenume,medic.getPrenume());

            if (distantaLevenshteinPrenume <=2 && distantaLevenshteinNume<=2)
                mediciCautati.add(medic);

            distantaLevenshteinNume = 0;
            distantaLevenshteinPrenume = 0;
        }
        return mediciCautati;
    }

}
