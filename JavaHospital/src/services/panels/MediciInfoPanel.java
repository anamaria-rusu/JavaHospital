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

        backButton.setBounds(50, 320, 100, 30);
    }
}
