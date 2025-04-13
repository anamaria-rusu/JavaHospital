package services.panels;

import entities.Medic;
import entities.Persoana;

import javax.swing.*;
import java.awt.*;

public class MediciInfoPanel extends PersoanaInfoPanel {

    private Medic medic;

    public MediciInfoPanel(Medic medic) {
        super(medic, "Informații Medic");
        this.medic = medic;
        setBackground(Color.decode("#b0e6de"));
        afiseazaInformatiiSpecifice();
        setBackButton("AfiseazaMedici");
    }

    @Override
    protected void afiseazaInformatiiSpecifice()
    {
        int y = 270;
        JLabel dataAngajariiLabel = new JLabel("Data angajarii:");
        dataAngajariiLabel.setBounds(50, y, 100, 30);
        add(dataAngajariiLabel);

        JLabel dataAngajariiPersoana = new JLabel(medic.getDataAngajarii().toString());
        dataAngajariiPersoana.setBounds(160, y, 200, 30);
        add(dataAngajariiPersoana);

        y+=40;
        JLabel departamentLabel = new JLabel("Departament:");
        departamentLabel.setBounds(50, y, 100, 30);
        add(departamentLabel);

        JLabel departamentPersoana = new JLabel(medic.getDepartamentMedical());
        departamentPersoana.setBounds(160, y, 200, 30);
        add(departamentPersoana);

        y+=50;
        backButton.setBounds(50, y, 100, 30);
    }
}
