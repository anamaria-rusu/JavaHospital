package entities;
import java.time.LocalDate;

public class Pacient extends Persoana {
    private static int nrPacienti = 0;
    private final int idPacient;
    private IstoricPacient istoricMedical;

    public Pacient() {
        super();
        this.idPacient = ++nrPacienti;
        this.istoricMedical = new IstoricPacient();

    }

    public Pacient(String nume, String prenume, LocalDate dataNasterii, String email, String telefon) {
        super(nume, prenume, dataNasterii, email, telefon);
        this.idPacient = ++nrPacienti;
        this.istoricMedical = new IstoricPacient();
    }

    public int getIdPacient() {
        return idPacient;
    }


    public IstoricPacient getIstoricMedical() {
        return istoricMedical;
    }

    public void adaugaIstoric(ServiciuMedical serviciuMedical){
        this.istoricMedical.getServiciiMedicale().add(serviciuMedical);
    }
}
