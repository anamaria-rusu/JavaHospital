package services.panels;

import services.services.Services;

import javax.swing.*;
import java.awt.*;

// PacientiPanel ofera functionalitatile pentru gestiunea pacientilor

public class PacientiPanel extends JPanel
{
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Services service;

    public PacientiPanel(Services service, CardLayout cardLayout, JPanel mainPanel)
    {
        int y = 30;

        this.service = service;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setBackground(Color.decode("#b0e1e6"));
        setLayout(null);

        JLabel menuLabel = new JLabel("Administrare Pacienți");
        menuLabel.setForeground(Color.BLACK);
        menuLabel.setFont(new Font("AvantGarde", Font.PLAIN, 38));
        menuLabel.setBounds(140, y, 400, 40);
        add(menuLabel);

        y+=70;
        JButton veziPacientiButton = new JButton("Vezi Pacienti");
        veziPacientiButton.setBounds(175, y, 250, 40);
        veziPacientiButton.addActionListener(e -> cardLayout.show(mainPanel, "AfiseazaPacienti"));
        add(veziPacientiButton);

        y+=60;
        JButton adaugaPacientButton = new JButton("Adauga Pacient");
        adaugaPacientButton.setBounds(175, y, 250, 40);
        adaugaPacientButton.addActionListener(e -> cardLayout.show(mainPanel, "AdaugaPacienti"));
        add(adaugaPacientButton);

        y+=60;
        JButton homePanelButton = new JButton("Inapoi");
        homePanelButton.setBounds(175, y, 250, 40);
        homePanelButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
        add(homePanelButton);

        mainPanel.add(new PacientiAddPanel(service, cardLayout, mainPanel), "AdaugaPacienti");
        mainPanel.add(new PacientiListPanel(service, cardLayout, mainPanel), "AfiseazaPacienti");
    }
}
