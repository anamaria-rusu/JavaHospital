package services.panels;
import services.services.MediciServices;
import services.services.Services;

import javax.swing.*;
import java.awt.*;

// MediciPanel ofera functionalitatile pentru gestiunea medicilor

public class MediciPanel extends JPanel{
    private CardLayout cardLayout;
    private JPanel parentPanel;
    private Services services;

    public MediciPanel(Services services,CardLayout cardLayout, JPanel parentPanel) {
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;
        this.services = services;


        //configurare UI

        setBackground(Color.decode("#B0E0E6"));
        setLayout(null);

        JLabel menuLabel = new JLabel("Administrare Medici");
        menuLabel.setForeground(Color.WHITE);
        menuLabel.setFont(new Font("AvantGarde", Font.BOLD, 38));
        menuLabel.setBounds(100, 30, 400, 40);
        add(menuLabel);

        JButton veziMediciButton = new JButton("Vezi Medici");
        veziMediciButton.setBounds(175, 100, 250, 40);
        veziMediciButton.addActionListener(e -> cardLayout.show(parentPanel, "AfiseazaMedici"));
        add(veziMediciButton);

        JButton adaugaMedicButton = new JButton("Adaugă Medic");
        adaugaMedicButton.setBounds(175, 160, 250, 40);
        adaugaMedicButton.addActionListener(e -> cardLayout.show(parentPanel, "AdaugaMedici"));
        add(adaugaMedicButton);

        JButton homePanelButton = new JButton("Înapoi");
        homePanelButton.setBounds(175, 220, 250, 40);
        homePanelButton.addActionListener(e -> cardLayout.show(parentPanel, "AngajatiPanel"));
        add(homePanelButton);


        //legatura cu AdaugaMedici si AfiseazaMedici
        parentPanel.add(new MediciAddPanel(services, cardLayout, parentPanel), "AdaugaMedici");
        parentPanel.add(new MediciListPanel(services, cardLayout, parentPanel), "AfiseazaMedici");

    }
}
