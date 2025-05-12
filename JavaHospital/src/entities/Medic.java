package entities;

import java.time.LocalDate;

public class Medic extends Angajat
{
    private final int idMedic;
    private String departamentMedical;

    public Medic(int idMedic,String nume, String prenume, LocalDate dataNasterii, String email, String telefon, LocalDate dataAngajarii, String departamentMedical) {
        super(nume, prenume, dataNasterii, email, telefon, dataAngajarii);
        this.departamentMedical = departamentMedical;
        this.idMedic = idMedic;
    }

    public String getDepartamentMedical() {
        return departamentMedical;
    }

    public void setDepartamentMedical(String departamentMedical) {
        this.departamentMedical = departamentMedical;
    }

    @Override
    public int getId() {
        return idMedic;
    }
}
