package entities;
import java.time.LocalDate;
import java.time.LocalTime;

public class Consultatie implements ServiciuMedical {
    private final int idConsultatie;
    private Medic medic;
    private Pacient pacient;
    private String departament;
    private LocalDate dataProgramare;
    private LocalTime oraProgramare;
    private int durataConsultatie;
    private String motiv;

    public Consultatie(int idConsultatie,Medic medic, Pacient pacient, String departament, LocalDate dataProgramare, LocalTime oraProgramare, int durataConsultatie, String motiv) {
        this.medic = medic;
        this.pacient = pacient;
        this.departament = departament;
        this.dataProgramare = dataProgramare;
        this.oraProgramare = oraProgramare;
        this.durataConsultatie = durataConsultatie;
        this.motiv = motiv;
        this.idConsultatie = idConsultatie;
    }

    public LocalDate getData() {
        return dataProgramare;
    }

    public String getDepartament() {
        return departament;
    }

    public String getMotiv() {
        return motiv;
    }


    public Medic getMedic() {
        return medic;
    }

    public void setMedic(Medic medic) {
        this.medic = medic;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }


    public void setDataProgramare(LocalDate dataProgramare) {
        this.dataProgramare = dataProgramare;
    }

    public String getDescriere() {
        return motiv;
    }

    public void setMotiv(String motiv) {
        this.motiv = motiv;
    }

    public int getDurataConsultatie() {
        return durataConsultatie;
    }

    public void setDurataConsultatie(int durataConsultatie) {
        this.durataConsultatie = durataConsultatie;
    }

    public LocalTime getOraProgramare() {
        return oraProgramare;
    }

    public void setOraProgramare(LocalTime oraProgramare) {
        this.oraProgramare = oraProgramare;
    }

    @Override
    public String getDepartamentMedical() {
        return departament;
    }

    @Override
    public int getId() {
        return idConsultatie;
    }

    public void setDepartamentMedical(String departament) {
        this.departament = departament;
    }
}
