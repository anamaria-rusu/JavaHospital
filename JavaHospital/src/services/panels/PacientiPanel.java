package services.panels;

import services.services.PacientiServices;
import javax.swing.*;
import java.awt.*;

public class PacientiPanel extends JPanel
{
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private PacientiServices service;

    public PacientiPanel(PacientiServices service, CardLayout cardLayout, JPanel mainPanel)
    {
        this.service = service;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setBackground(Color.decode("#89CFF0"));
        setLayout(null); // Folosim null layout pentru a controla poziția exactă a componentelor

        JLabel menuLabel = new JLabel("Administrare Pacienți");
        menuLabel.setForeground(Color.WHITE);
        menuLabel.setFont(new Font("AvantGarde", Font.BOLD, 38));
        menuLabel.setBounds(100, 30, 400, 40);
        add(menuLabel);

        JButton veziPacientiButton = new JButton("Vezi Pacienți");
        veziPacientiButton.setBounds(175, 100, 250, 40);
        veziPacientiButton.addActionListener(e -> cardLayout.show(mainPanel, "AfiseazaPacienti"));
        add(veziPacientiButton);

        JButton adaugaPacientButton = new JButton("Adaugă Pacient");
        adaugaPacientButton.setBounds(175, 160, 250, 40);
        adaugaPacientButton.addActionListener(e -> cardLayout.show(mainPanel, "AdaugaPacienti"));
        add(adaugaPacientButton);


        //"Home" trebuie să fie numele unui panou adăugat anterior în mainPanel folosind mainPanel.add(homePanel, "Home").
        JButton homePanelButton = new JButton("Înapoi");
        homePanelButton.setBounds(175, 220, 250, 40);
        homePanelButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
        add(homePanelButton);

        // Înregistrează panoul de pacienți cu un nume corect
        // mainPanel.add(this, "PacientiPanel");

        // Adaugă celelalte panouri în mainPanel
        mainPanel.add(new PacientiAddPanel(service, cardLayout, mainPanel), "AdaugaPacienti");
        mainPanel.add(new PacientiListPanel(service, cardLayout, mainPanel), "AfiseazaPacienti");
    }
}
