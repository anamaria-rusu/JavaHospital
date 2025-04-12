package services.panels;

import entities.IstoricPacient;
import entities.Pacient;
import services.services.Services;

import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;

public class PacientiInfoPanel extends PersoanaInfoPanel {
    private Pacient pacient;
    private JButton istoricButton;
    private CardLayout cardLayout;
    private JPanel parentPanel;
    private Services services;

    public PacientiInfoPanel(Pacient pacient, String backPanel, CardLayout cardLayout, JPanel parentPanel, Services service) {
        super(pacient, "Informații Pacient");
        this.pacient = pacient;
        this.cardLayout =cardLayout;
        this.parentPanel = parentPanel;
        this.services=service;
        setBackButton(backPanel);

        istoricButton = new JButton("Istoric Medical");
        istoricButton.setBounds(50, 500, 100, 30);
        istoricButton.addActionListener(e->cardLayout.show(parentPanel, "IstoricPacient") );
        add(istoricButton);
        parentPanel.add(new IstoricPacientPanel(pacient,cardLayout,parentPanel,services), "IstoricPacient");
    }

    @Override
    protected void afiseazaInformatiiSpecifice()
    {

    }
}
