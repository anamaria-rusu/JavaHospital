package entities;

import java.util.ArrayList;
import java.util.List;

public class IstoricPacient
{
    private List<ServiciuMedical>serviciiMedicale;

    public IstoricPacient(){
        serviciiMedicale = new ArrayList<>();
    }

    public List<ServiciuMedical> getServiciiMedicale() {
        return serviciiMedicale;
    }

    public void adaugaServiciu(ServiciuMedical serviciuMedical) {
        serviciiMedicale.add(serviciuMedical);
    }
}
