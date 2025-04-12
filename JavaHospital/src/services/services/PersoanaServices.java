package services.services;

import entities.Persoana;
import java.util.List;

public interface PersoanaServices<T extends Persoana> {
    List<T> getPersoane();
    List<T> cautaPersoane(String nume, String prenume);
}
