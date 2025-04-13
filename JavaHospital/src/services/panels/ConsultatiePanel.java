package services.panels;

import services.services.Services;

import javax.swing.*;
import java.awt.*;

public class ConsultatiePanel extends JPanel {

    private CardLayout cardLayout;
    private JPanel parentPanel;
    private Services serives;

    public ConsultatiePanel(CardLayout cardLayout, JPanel parentPanel, Services serives)
    {
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;
        this.serives = serives;

        setBackground(Color.decode("#c1e6b0"));
        setLayout(null);

        int y=30;
        JLabel menuLabel = new JLabel("Consultatii");
        menuLabel.setForeground(Color.WHITE);
        menuLabel.setFont(new Font("AvantGarde", Font.PLAIN, 38));
        menuLabel.setBounds(200, y, 400, 40);
        add(menuLabel);

        y+=70;
        JButton veziConsulatieButton = new JButton("Vezi Consultatii");
        veziConsulatieButton.setBounds(175, y, 250, 40);
        veziConsulatieButton.addActionListener(e -> cardLayout.show(parentPanel, "AfiseazaConsultatie"));
        add(veziConsulatieButton);

        y+=60;
        JButton adaugaConsultatieButton = new JButton("Adauga Consultatie");
        adaugaConsultatieButton.setBounds(175, y, 250, 40);
        adaugaConsultatieButton.addActionListener(e -> cardLayout.show(parentPanel, "AdaugaConsultatie"));
        add(adaugaConsultatieButton);

        y+=60;
        JButton homePanelButton = new JButton("Inapoi");
        homePanelButton.setBounds(175, y, 250, 40);
        homePanelButton.addActionListener(e -> cardLayout.show(parentPanel, "Home"));
        add(homePanelButton);

        parentPanel.add(new ConsultatieAddPanel(serives, cardLayout, parentPanel), "AdaugaConsultatie");
        parentPanel.add(new ConsultatieListPanel(serives, cardLayout, parentPanel), "AfiseazaConsultatie");

    }
}
