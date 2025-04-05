package services.panels;

import services.services.ConsultatieService;
import services.services.PacientiServices;
import services.services.Services;

import javax.swing.*;
import java.awt.*;

public class ConsultatiePanel extends JPanel {

    private CardLayout cardLayout;
    private JPanel parentPanel;
    private Services serives;

    public ConsultatiePanel(CardLayout cardLayout, JPanel parentPanel, Services serives) {
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;
        this.serives = serives;

        setBackground(Color.decode("#93C4A4"));
        setLayout(null);

        JLabel menuLabel = new JLabel("Consultatii");
        menuLabel.setForeground(Color.WHITE);
        menuLabel.setFont(new Font("AvantGarde", Font.BOLD, 38));
        menuLabel.setBounds(200, 30, 400, 40);
        add(menuLabel);

        JButton veziConsulatieButton = new JButton("Vezi Consultatii");
        veziConsulatieButton.setBounds(175, 100, 250, 40);
        veziConsulatieButton.addActionListener(e -> cardLayout.show(parentPanel, "AfiseazaConsultatie"));
        add(veziConsulatieButton);

        JButton adaugaConsultatieButton = new JButton("Adauga Consultatie");
        adaugaConsultatieButton.setBounds(175, 160, 250, 40);
        adaugaConsultatieButton.addActionListener(e -> cardLayout.show(parentPanel, "AdaugaConsultatie"));
        add(adaugaConsultatieButton);

        JButton homePanelButton = new JButton("Înapoi");
        homePanelButton.setBounds(175, 220, 250, 40);
        homePanelButton.addActionListener(e -> cardLayout.show(parentPanel, "Home"));
        add(homePanelButton);


        //legatura cu AdaugaMedici si AfiseazaMedici
        parentPanel.add(new ConsultatieAddPanel(serives, cardLayout, parentPanel), "AdaugaConsultatie");
        parentPanel.add(new ConsultatieListPanel(serives, cardLayout, parentPanel), "AfiseazaConsultatie");

    }
}
