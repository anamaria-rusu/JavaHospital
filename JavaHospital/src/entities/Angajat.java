package entities;
import java.time.LocalDate;

public abstract class Angajat extends Persoana{
    private LocalDate dataAngajarii;
    private static int nrAngajati = 2000;
    private final int idAngajat;


    public Angajat() {
        super();
        this.dataAngajarii = LocalDate.of(1970,1,1);
        this.idAngajat = ++ nrAngajati;

    }

    public Angajat(String nume, String prenume, LocalDate dataNasterii, String email, String telefon, LocalDate dataAngajarii) {
        super(nume,prenume,dataNasterii,email,telefon);
        this.dataAngajarii= dataAngajarii;
        this.idAngajat = ++ nrAngajati;
    }

    public LocalDate getDataAngajarii() {
        return dataAngajarii;
    }

    public void setDataAngajarii(LocalDate dataAngajarii) {
        this.dataAngajarii = dataAngajarii;
    }

    @Override
    public int getId() {
        return idAngajat;
    }
}
