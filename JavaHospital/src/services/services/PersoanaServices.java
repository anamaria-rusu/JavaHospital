package services.services;
import entities.Pacient;
import entities.Persoana;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.ArrayList;
import java.util.List;

public interface PersoanaServices<T extends Persoana>
{
    LevenshteinDistance motorCautare = new LevenshteinDistance();

    List<T> getPersoane();


    default List<T> cautaPersoane(String nume, String prenume)
    {
        List<T> persoane = getPersoane();
        List<T> persoaneCautate = new ArrayList<>();
        int distantaLevenshteinNume = 0;
        int distantaLevenshteinPrenume = 0;

        for(T persoana : persoane){

            if(!nume.isEmpty())
                distantaLevenshteinNume =  motorCautare.apply(nume,persoana.getNume());
            if(!prenume.isEmpty())
                distantaLevenshteinPrenume =  motorCautare.apply(prenume,persoana.getPrenume());

            if (distantaLevenshteinPrenume <=2 && distantaLevenshteinNume<=2)
                persoaneCautate.add(persoana);

            distantaLevenshteinNume = 0;
            distantaLevenshteinPrenume = 0;
        }
        return persoaneCautate;
    }

}
