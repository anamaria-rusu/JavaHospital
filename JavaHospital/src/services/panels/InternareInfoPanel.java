package services.panels;

import entities.Pacient;
import entities.Internare;
import services.services.InternareServices;
import services.services.Services;

import javax.swing.*;
import java.awt.*;

public class InternareInfoPanel extends PacientiInfoPanel {
    private Internare internare;

    public InternareInfoPanel(Internare internare, String backPanel, CardLayout cardLayout, JPanel parentPanel, Services services) {
        super(internare.getPacient() ,"Informații Pacient Internat",cardLayout,parentPanel,services);
        this.internare = internare;
        afiseazaInformatiiSpecifice();
        setBackButton(backPanel);
    }

    @Override
    protected void afiseazaInformatiiSpecifice() {
        JLabel dataInternariiLabel = new JLabel("Data internarii:");
        dataInternariiLabel.setBounds(50, 270, 100, 30);
        add(dataInternariiLabel);

        JLabel  dataInternarii = new JLabel(internare.getData().toString());
        dataInternarii.setBounds(160, 270, 200, 30);
        add( dataInternarii);

        JLabel diagnosticLabel = new JLabel("Departament:");
        diagnosticLabel.setBounds(50, 310, 100, 30);
        add(diagnosticLabel);

        JLabel diagnostic = new JLabel(internare.getDescriere());
        diagnostic.setBounds(160, 310, 200, 30);
        add(diagnostic);

        JLabel departamentLabel = new JLabel("Departament:");
        departamentLabel.setBounds(50, 350, 100, 30);
        add(departamentLabel);

        JLabel departamentPersoana = new JLabel(internare.getDepartamentMedical());
        departamentPersoana.setBounds(160, 350, 200, 30);
        add(departamentPersoana);

        backButton.setBounds(50, 400, 100, 30);
    }

}

