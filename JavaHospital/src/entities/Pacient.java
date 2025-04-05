package entities;
import java.time.LocalDate;

public class Pacient extends Persoana {
    private static int nrPacienti = 0;
    private final int idPacient;
    private boolean internat;

    public Pacient() {
        super();
        this.idPacient = ++nrPacienti;
        this.internat = false;
    }

    public Pacient(String nume, String prenume, LocalDate dataNasterii, String email, String telefon) {
        super(nume, prenume, dataNasterii, email, telefon);
        this.idPacient = ++nrPacienti;
        this.internat = false;
    }

    public int getIdPacient() {
        return idPacient;
    }
}
