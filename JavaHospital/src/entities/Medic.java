package entities;

import java.time.LocalDate;

public class Medic extends Angajat{
    private String departamentMedical;

    public Medic()
    {
        super();
        departamentMedical = "";
    }

    public Medic(String nume, String prenume, LocalDate dataNasterii, String email, String telefon, LocalDate dataAngajarii, String departamentMedical) {
        super(nume, prenume, dataNasterii, email, telefon, dataAngajarii);
        this.departamentMedical = departamentMedical;
    }

    public Medic(String departamentMedical) {
        this.departamentMedical = departamentMedical;
    }

    public String getDepartamentMedical() {
        return departamentMedical;
    }

    public void setDepartamentMedical(String departamentMedical) {
        this.departamentMedical = departamentMedical;
    }
}
