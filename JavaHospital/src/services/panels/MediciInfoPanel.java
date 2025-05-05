package services.panels;

import entities.Medic;

import javax.swing.*;
import java.awt.*;

public class MediciInfoPanel extends PersoanaInfoPanel {

    private final Medic medic;

    public MediciInfoPanel(Medic medic) {

        super(medic, "Informaii Medic");
        this.medic = medic;

        setBackground(Color.decode("#b0e6de"));

        afiseazaInformatiiSpecifice();
        setBackButton("AfiseazaMedici");
    }

    @Override
    protected void afiseazaInformatiiSpecifice()
    {
        int y = 270;

        addLabel("Data angajării:", medic.getDataAngajarii().toString(), y);
        y += 40;

        addLabel("Departament:", medic.getDepartamentMedical(), y);
        y += 50;

        backButton.setBounds(50, y, 100, 30);
    }


    private void addLabel(String title, String value, int y)
    {
        JLabel titleLabel = new JLabel(title);
        titleLabel.setBounds(50, y, 100, 30);
        add(titleLabel);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setBounds(160, y, 200, 30);
        add(valueLabel);
    }
}
