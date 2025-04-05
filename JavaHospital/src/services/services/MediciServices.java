package services.services;

import entities.Medic;
import entities.Pacient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MediciServices implements PersoanaServices<Medic>{

    private List<Medic> medici = new ArrayList<>();


    public MediciServices() {
        // Adăugăm un medic inițial în listă
        Medic medic = new Medic("Andrei", "Ionescu", LocalDate.of(1975, 10, 25), "andrei.ionescu@email.com", "0745123456", LocalDate.of(2005, 6, 10), "Cardiologie");
        medici.add(medic);
    }


    public void adaugaMedic(String nume, String prenume, LocalDate dataNasterii, String email, String telefon, LocalDate dataAngajarii, String departament) {
        medici.add(new Medic(nume,prenume,dataNasterii,telefon,email,dataAngajarii,departament));
    }

    public List<Medic> getPersoane() {
        return new ArrayList<>(medici);
    }

}
