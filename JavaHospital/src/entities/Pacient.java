package entities;

import java.time.LocalDate;

public class Pacient {
    private static int nrPacienti;
    private final  int  idPacient;
    private String nume;
    private String prenume;
    private LocalDate dataNasterii;
    private String telefon;
    private String email;

    static
    {
        nrPacienti++;
    }

    {
        idPacient = ++ nrPacienti;
    }
    public Pacient() {
        this.nume = "";
        this.prenume = "";
        this.dataNasterii = LocalDate.of(0,0,0);
        this.email = "";
        this.telefon = "";
    }

    public Pacient(String nume, String prenume, LocalDate dataNasterii, String email, String telefon) {
        this.nume = nume;
        this.prenume = prenume;
        this.dataNasterii = dataNasterii;
        this.email = email;
        this.telefon = telefon;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public LocalDate getDataNasterii() {
        return dataNasterii;
    }

    public void setDataNasterii(LocalDate dataNasterii) {
        this.dataNasterii = dataNasterii;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public int getIdPacient(){
        return this.idPacient;
    }
}
