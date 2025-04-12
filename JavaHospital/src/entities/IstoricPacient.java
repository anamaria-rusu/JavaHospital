package entities;

import java.util.ArrayList;
import java.util.List;

public class IstoricPacient {
    private static int nrIstoric = 0;
    private final int idIstoric;
    private List<ServiciuMedical>serviciiMedicale;

    public IstoricPacient() {
        this.idIstoric = ++ nrIstoric;
        this.serviciiMedicale = new ArrayList<>();
    }

    public IstoricPacient(Pacient pacient) {
        this.idIstoric = ++ nrIstoric;
    }

    public List<ServiciuMedical> getServiciiMedicale() {
        return serviciiMedicale;
    }

    public int getIdIstoric() {
        return idIstoric;
    }

}
