package entities;

import java.time.LocalDate;

public abstract class Persoana {
    private static int nrPersoana = 0;
    private final int idPersoana;
    private String nume;
    private String prenume;
    private LocalDate dataNasterii;
    private String telefon;
    private String email;

    public Persoana() {
        this.idPersoana = nrPersoana++;
        this.nume = "";
        this.prenume = "";
        this.dataNasterii = LocalDate.of(1970,1,1);
        this.email = "";
        this.telefon = "";
    }

    public Persoana(String nume, String prenume, LocalDate dataNasterii, String email, String telefon) {
        this.nume = nume;
        this.prenume = prenume;
        this.dataNasterii = dataNasterii;
        this.email = email;
        this.telefon = telefon;
        this.idPersoana = nrPersoana++;
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

    public abstract int getId();

}
