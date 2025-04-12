package services.panels;
import entities.Consultatie;

import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;

public class ConsultatieInfoPanel extends JPanel{
    private JButton backButton;
    private CardLayout cardLayout;
    private JPanel parentPanel;

    public ConsultatieInfoPanel(Consultatie consultatie, CardLayout cardLayout, JPanel parentPanel)
    {
        this.cardLayout=cardLayout;
        this.parentPanel=parentPanel;

        setBackground(Color.decode("#B0E0E6"));
        setLayout(null);

        // Titlu
        JLabel titleLabel = new JLabel("Consultatie");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(50, 20, 300, 30);
        add(titleLabel);

       // Medic
        JLabel medicLabel = new JLabel("Medic:");
        medicLabel.setBounds(50, 70, 100, 30);
        add(medicLabel);

        JLabel infoMedic = new JLabel(consultatie.getMedic().getNume()+" "+ consultatie.getMedic().getPrenume());
        infoMedic.setBounds(160, 70, 200, 30);
        add(infoMedic);


        // Pacient
        JLabel pacientLabel = new JLabel("Pacient:");
        pacientLabel.setBounds(50, 110, 100, 30);
        add(pacientLabel);

        JLabel infoPacient = new JLabel(consultatie.getPacient().getNume()+" "+ consultatie.getPacient().getPrenume());
        infoPacient.setBounds(160, 110, 200, 30);
        add(infoPacient);

        // data programare
        JLabel dataLabel = new JLabel("Data programarii:");
        dataLabel.setBounds(50, 150, 100, 30);
        add(dataLabel);

        JLabel data = new JLabel(consultatie.getData().toString());
        data.setBounds(160, 150, 200, 30);
        add(data);

        // ora prog
        JLabel oraLabel = new JLabel("Ora:");
        oraLabel.setBounds(50, 190, 100, 30);
        add(oraLabel);

        JLabel ora = new JLabel(consultatie.getOraProgramare().toString());
        ora.setBounds(160, 190, 200, 30);
        add(ora);

        // durata
        JLabel durataLabel = new JLabel("Durata estimata:");
        durataLabel.setBounds(50, 230, 100, 30);
        add(durataLabel);

        JLabel durata = new JLabel(consultatie.getDurataConsultatie() + " minute");
        durata.setBounds(160, 230, 200, 30);
        add(durata);

        // motiv
        JLabel motivLabel = new JLabel("Motiv:");
        motivLabel.setBounds(50, 270, 100, 30);
        add(motivLabel);

        JLabel motiv = new JLabel(consultatie.getDescriere());
        motiv.setBounds(160, 270, 200, 30);
        add(motiv);

        // Buton pentru întoarcere
        backButton = new JButton("Inapoi");
        backButton.setBounds(50, 320, 100, 30);
        backButton.addActionListener(e-> cardLayout.show(parentPanel, "AfiseazaConsultatie"));
        add(backButton);

    }
}
