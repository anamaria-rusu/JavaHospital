package entities;

import java.time.LocalDate;

public class Internare implements ServiciuMedical{
    private final int idInternare;
    private Pacient pacient;
    private LocalDate dataInternare;
    private String diagnostic;
    private String departamentMedical;
    private Salon salon;

    public Internare(int idInternare,Pacient pacient,Salon salon,String diagnostic, String departamentMedical) {
        this.pacient = pacient;
        this.dataInternare = LocalDate.now();
        this.diagnostic = diagnostic;
        this.idInternare = idInternare;
        this.departamentMedical=departamentMedical;
        this.salon=salon;
    }

    public Internare(int idInternare,Pacient pacient,Salon salon,String diagnostic, String departamentMedical, LocalDate dataInternare) {
        this.pacient = pacient;
        this.diagnostic = diagnostic;
        this.idInternare = idInternare;
        this.departamentMedical=departamentMedical;
        this.salon=salon;
        this.dataInternare=dataInternare;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public LocalDate getData() {
        return dataInternare;
    }

    public void setDataInternare(LocalDate dataInternare) {
        this.dataInternare = dataInternare;
    }

    public String getDescriere() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    @Override
    public String getDepartamentMedical() {
        return departamentMedical;
    }

    public void setDepartamentMedical(String departamentMedical) {
        this.departamentMedical = departamentMedical;
    }

    @Override
    public int getId() {
        return idInternare;
    }


    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }


    public LocalDate getDataInternare() {
        return dataInternare;
    }

    public String getDiagnostic() {
        return diagnostic;
    }
}
