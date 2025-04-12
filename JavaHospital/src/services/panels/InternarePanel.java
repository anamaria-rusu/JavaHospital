package services.panels;
import services.services.Services;

import javax.swing.*;
import java.awt.*;

public class InternarePanel extends JPanel {

    Services services;
    CardLayout cardLayout;
    JPanel parentPanel;

    public InternarePanel(Services services, CardLayout cardLayout, JPanel parentPanel) {
        this.services=services;
        this.cardLayout=cardLayout;
        this.parentPanel=parentPanel;

        setBackground(Color.decode("#9E9765"));
        setLayout(null);

        JLabel menuLabel = new JLabel("Internari");
        menuLabel.setForeground(Color.WHITE);
        menuLabel.setFont(new Font("AvantGarde", Font.BOLD, 38));
        menuLabel.setBounds(100, 30, 400, 40);
        add(menuLabel);

        JButton saloanebutton = new JButton("Saloane");
        saloanebutton.setBounds(175, 100, 250, 40);
        saloanebutton.addActionListener(e -> cardLayout.show(parentPanel, "SaloanePanel"));
        add(saloanebutton);

        JButton adaugaInternareButton = new JButton("Adauga Internare");
        adaugaInternareButton.setBounds(175, 160, 250, 40);
        adaugaInternareButton.addActionListener(e -> cardLayout.show(parentPanel, "AdaugaInternare"));
        add(adaugaInternareButton);


        JButton homePanelButton = new JButton("Inapoi");
        homePanelButton.setBounds(175, 220, 250, 40);
        homePanelButton.addActionListener(e -> cardLayout.show(parentPanel, "Home"));
        add(homePanelButton);

        parentPanel.add(new SalonPanel(services, cardLayout, parentPanel), "SaloanePanel");
        parentPanel.add(new InternareAddPanel(services, cardLayout, parentPanel), "AdaugaInternare");

    }
}
