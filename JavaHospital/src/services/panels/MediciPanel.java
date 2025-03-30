package services.panels;
import services.services.MediciServices;

import javax.swing.*;
import java.awt.*;

// MediciPanel ofera functionalitatile pentru gestiunea medicilor

public class MediciPanel extends JPanel{
    private CardLayout cardLayout;
    private JPanel parentPanel;
    private MediciServices mediciService;

    public MediciPanel(CardLayout cardLayout, JPanel parentPanel) {
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;
        mediciService = new MediciServices();


        //configurare UI

        setBackground(Color.decode("#B0E0E6"));
        setLayout(null);

        JLabel menuLabel = new JLabel("Administrare Medici");
        menuLabel.setForeground(Color.WHITE);
        menuLabel.setFont(new Font("AvantGarde", Font.BOLD, 38));
        menuLabel.setBounds(100, 30, 400, 40);
        add(menuLabel);

        JButton veziPacientiButton = new JButton("Vezi Medici");
        veziPacientiButton.setBounds(175, 100, 250, 40);
        veziPacientiButton.addActionListener(e -> cardLayout.show(parentPanel, "AfiseazaMedici"));
        add(veziPacientiButton);

        JButton adaugaPacientButton = new JButton("Adaugă Medic");
        adaugaPacientButton.setBounds(175, 160, 250, 40);
        adaugaPacientButton.addActionListener(e -> cardLayout.show(parentPanel, "AdaugaMedici"));
        add(adaugaPacientButton);

        JButton homePanelButton = new JButton("Înapoi");
        homePanelButton.setBounds(175, 220, 250, 40);
        homePanelButton.addActionListener(e -> cardLayout.show(parentPanel, "AngajatiPanel"));
        add(homePanelButton);


        //legatura cu AdaugaMedici si AfiseazaMedici
        parentPanel.add(new MediciAddPanel(mediciService, cardLayout, parentPanel), "AdaugaMedici");
        //parentPanel.add(new MediciListPanel(mediciService, cardLayout, parentPanel), "AfiseazaMedici");

    }
}
