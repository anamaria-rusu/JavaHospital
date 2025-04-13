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

    public MediciPanel(Services services,CardLayout cardLayout, JPanel parentPanel)
    {
        int y = 30;

        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;
        this.services = services;

        setBackground(Color.decode("#b0e6de"));
        setLayout(null);

        JLabel menuLabel = new JLabel("Administrare Medici");
        menuLabel.setForeground(Color.BLACK);
        menuLabel.setFont(new Font("AvantGarde", Font.PLAIN, 38));
        menuLabel.setBounds(100, y, 400, 40);
        add(menuLabel);

        y+=70;
        JButton veziMediciButton = new JButton("Vezi Medici");
        veziMediciButton.setBounds(175, y, 250, 40);
        veziMediciButton.addActionListener(e -> cardLayout.show(parentPanel, "AfiseazaMedici"));
        add(veziMediciButton);

        y+=60;
        JButton adaugaMedicButton = new JButton("Adaugă Medic");
        adaugaMedicButton.setBounds(175, y, 250, 40);
        adaugaMedicButton.addActionListener(e -> cardLayout.show(parentPanel, "AdaugaMedici"));
        add(adaugaMedicButton);

        y+=60;
        JButton homePanelButton = new JButton("Înapoi");
        homePanelButton.setBounds(175, y, 250, 40);
        homePanelButton.addActionListener(e -> cardLayout.show(parentPanel, "AngajatiPanel"));
        add(homePanelButton);

        parentPanel.add(new MediciAddPanel(services, cardLayout, parentPanel), "AdaugaMedici");
        parentPanel.add(new MediciListPanel(services, cardLayout, parentPanel), "AfiseazaMedici");

    }
}
