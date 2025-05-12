package services.panels;

import javax.swing.*;
import java.awt.*;

public class InternarePanel extends JPanel {

    CardLayout cardLayout;
    JPanel parentPanel;

    public InternarePanel(CardLayout cardLayout, JPanel parentPanel) {

        this.cardLayout=cardLayout;
        this.parentPanel=parentPanel;

        setBackground(Color.decode("#e6e2b0"));
        setLayout(null);

        JLabel menuLabel = new JLabel("Internari");
        menuLabel.setForeground(Color.BLACK);
        menuLabel.setFont(new Font("AvantGarde", Font.PLAIN, 38));
        menuLabel.setBounds(200, 30, 400, 40);
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

        parentPanel.add(new SalonPanel(cardLayout, parentPanel), "SaloanePanel");
        parentPanel.add(new InternareAddPanel(cardLayout, parentPanel), "AdaugaInternare");

    }
}
