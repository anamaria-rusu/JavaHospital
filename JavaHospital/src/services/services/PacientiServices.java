package services.services;

import entities.Pacient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PacientiServices implements PersoanaServices<Pacient> {

    private List<Pacient> pacienti = new ArrayList<>();

    public void adaugaPacient(String nume, String prenume, LocalDate dataNasterii, String email, String telefon) {
        pacienti.add(new Pacient(nume,prenume,dataNasterii,telefon,email));
    }

    public List<Pacient> getPersoane() {
        return new ArrayList<>(pacienti);
    }
}


