package services.panels;

import entities.Consultatie;

import javax.swing.*;
import java.awt.*;

public class ConsultatieInfoPanel extends JPanel {
    private JButton backButton;
    private CardLayout cardLayout;
    private JPanel parentPanel;

    public ConsultatieInfoPanel(Consultatie consultatie, CardLayout cardLayout, JPanel parentPanel) {
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setBackground(Color.decode("#c1e6b0"));
        setLayout(null);

        int y = 20;

        // Titlu
        JLabel titleLabel = new JLabel("Consultatie");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(50, y, 300, 30);
        add(titleLabel);

        y += 50;

        // Medic
        JLabel medicLabel = new JLabel("Medic:");
        medicLabel.setBounds(50, y, 100, 30);
        add(medicLabel);

        JLabel infoMedic = new JLabel(consultatie.getMedic().getNume() + " " + consultatie.getMedic().getPrenume());
        infoMedic.setBounds(160, y, 200, 30);
        add(infoMedic);

        y += 40;

        // Pacient
        JLabel pacientLabel = new JLabel("Pacient:");
        pacientLabel.setBounds(50, y, 100, 30);
        add(pacientLabel);

        JLabel infoPacient = new JLabel(consultatie.getPacient().getNume() + " " + consultatie.getPacient().getPrenume());
        infoPacient.setBounds(160, y, 200, 30);
        add(infoPacient);

        y += 40;

        // Data programare
        JLabel dataLabel = new JLabel("Data programarii:");
        dataLabel.setBounds(50, y, 120, 30);
        add(dataLabel);

        JLabel data = new JLabel(consultatie.getData().toString());
        data.setBounds(160, y, 200, 30);
        add(data);

        y += 40;

        // Ora programare
        JLabel oraLabel = new JLabel("Ora:");
        oraLabel.setBounds(50, y, 100, 30);
        add(oraLabel);

        JLabel ora = new JLabel(consultatie.getOraProgramare().toString());
        ora.setBounds(160, y, 200, 30);
        add(ora);

        y += 40;

        // Durata estimata
        JLabel durataLabel = new JLabel("Durata estimata:");
        durataLabel.setBounds(50, y, 120, 30);
        add(durataLabel);

        JLabel durata = new JLabel(consultatie.getDurataConsultatie() + " minute");
        durata.setBounds(160, y, 200, 30);
        add(durata);

        y += 40;

        // Motiv
        JLabel motivLabel = new JLabel("Motiv:");
        motivLabel.setBounds(50, y, 100, 30);
        add(motivLabel);

        JLabel motiv = new JLabel(consultatie.getDescriere());
        motiv.setBounds(160, y, 200, 30);
        add(motiv);

        y += 50;

        // Buton pentru întoarcere
        backButton = new JButton("Inapoi");
        backButton.setBounds(50, y, 100, 30);
        backButton.addActionListener(e -> cardLayout.show(parentPanel, "AfiseazaConsultatie"));
        add(backButton);
    }
}
