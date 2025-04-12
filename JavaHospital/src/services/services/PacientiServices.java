package services.services;
import entities.Pacient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entities.ServiciuMedical;
import org.apache.commons.text.similarity.LevenshteinDistance;
//https://commons.apache.org/proper/commons-text/apidocs/org/apache/commons/text/similarity/LevenshteinDistance.html


public class PacientiServices implements PersoanaServices<Pacient> {

    private List<Pacient> pacienti = new ArrayList<>();
    private LevenshteinDistance motorCautare = new LevenshteinDistance();


    public PacientiServices() {
        // Adăugăm un pacient inițial în listă
        Pacient pacient = new Pacient("Popescu", "Ion", LocalDate.of(1980, 5, 15), "0721123456", "ion.popescu@email.com");
        pacienti.add(pacient);

        Pacient pacient2 = new Pacient("Rusu", "Ana", LocalDate.of(1980, 5, 15), "0721123456", "ion.popescu@email.com");
        pacienti.add(pacient2);
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

    public List<Pacient> cautaPersoane(String nume, String prenume){
        List<Pacient> pacientiCautati = new ArrayList<>();
        int distantaLevenshteinNume = 0;
        int distantaLevenshteinPrenume = 0;

        for(Pacient pacinet : pacienti){

            if(!nume.isEmpty())
                distantaLevenshteinNume =  motorCautare.apply(nume,pacinet.getNume());
            if(!prenume.isEmpty())
                distantaLevenshteinPrenume =  motorCautare.apply(nume,pacinet.getPrenume());

            if (distantaLevenshteinPrenume <=2 && distantaLevenshteinNume<=2)
                pacientiCautati.add(pacinet);

            distantaLevenshteinNume = 0;
            distantaLevenshteinPrenume = 0;
        }
        return pacientiCautati;
    }

    public void adaugaIstoric(ServiciuMedical serviciuMedical)
    {

    }
}


