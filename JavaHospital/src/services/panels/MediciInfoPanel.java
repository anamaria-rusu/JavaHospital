package services.panels;

import entities.Medic;
import entities.Persoana;

import javax.swing.*;

public class MediciInfoPanel extends PersoanaInfoPanel {

    private Medic medic;

    public MediciInfoPanel(Medic medic) {
        super(medic, "Informații Medic");
        this.medic = medic;
        afiseazaInformatiiSpecifice();
        setBackButton("AfiseazaMedici");
    }

    @Override
    protected void afiseazaInformatiiSpecifice() {
        JLabel dataAngajariiLabel = new JLabel("Data angajarii:");
        dataAngajariiLabel.setBounds(50, 270, 100, 30);
        add(dataAngajariiLabel);

        JLabel dataAngajariiPersoana = new JLabel(medic.getDataAngajarii().toString());
        dataAngajariiPersoana.setBounds(160, 270, 200, 30);
        add(dataAngajariiPersoana);

        JLabel departamentLabel = new JLabel("Departament:");
        departamentLabel.setBounds(50, 310, 100, 30);
        add(departamentLabel);

        JLabel departamentPersoana = new JLabel(medic.getDepartamentMedical());
        departamentPersoana.setBounds(160, 310, 200, 30);
        add(departamentPersoana);

        backButton.setBounds(50, 360, 100, 30);
    }
}
