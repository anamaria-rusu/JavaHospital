package entities;
import java.time.LocalDate;

public class Pacient extends Persoana {
    private static int nrPacienti;
    private final  int  idPacient;

    static
    {
        nrPacienti++;
    }

    {
        idPacient = ++ nrPacienti;
    }
    public Pacient() {
        super();
    }

    public Pacient(String nume, String prenume, LocalDate dataNasterii, String email, String telefon) {
       super(nume,prenume,dataNasterii,email,telefon);
    }
}
