package entities;

import java.time.LocalDate;

public class Pacient extends Persoana
{
    private final int idPacient;

    public Pacient(int idPacient,String nume, String prenume, LocalDate dataNasterii, String email, String telefon) {
        super(nume, prenume, dataNasterii, email, telefon);
        this.idPacient = idPacient;
    }

    @Override
    public int getId() {
        return idPacient;
    }

}
