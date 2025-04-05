package services.services;

import entities.Pacient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PacientiServices implements PersoanaServices<Pacient> {

    private List<Pacient> pacienti = new ArrayList<>();


    public PacientiServices() {
        // Adăugăm un pacient inițial în listă
        Pacient pacient = new Pacient("Ion", "Popescu", LocalDate.of(1980, 5, 15), "0721123456", "ion.popescu@email.com");
        pacienti.add(pacient);
    }



    public void adaugaPacient(String nume, String prenume, LocalDate dataNasterii, String email, String telefon) {
        pacienti.add(new Pacient(nume,prenume,dataNasterii,telefon,email));
    }

    public List<Pacient> getPersoane() {
        return new ArrayList<>(pacienti);
    }

    public Pacient cautaPacient(int id) {
        for (Pacient p : pacienti) {
            if (p.getIdPacient() == id) {
                return p;
            }
        }
        return null;
    }
}


