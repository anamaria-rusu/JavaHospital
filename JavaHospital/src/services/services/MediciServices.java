package services.services;

import entities.Medic;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MediciServices {

    private List<Medic> medici = new ArrayList<>();

    public void adaugaMedic(String nume, String prenume, LocalDate dataNasterii, String email, String telefon, LocalDate dataAngajarii, String departament) {
        medici.add(new Medic(nume,prenume,dataNasterii,telefon,email,dataAngajarii,departament));
    }

    public List<Medic> getMedici() {
        return new ArrayList<>(medici);
    }
}
