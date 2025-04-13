package services.panels;

import entities.Pacient;
import services.services.Services;

import javax.swing.*;
import java.awt.*;

public class IstoricPacientPanel extends JPanel {
    private Services services;
    private CardLayout cardLayout;
    private JPanel parentPanel;

    public IstoricPacientPanel(Pacient pacient, CardLayout cardLayout, JPanel parentPanel, Services service) {
        this.services = service;
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setBackground(Color.decode("#ccdee3"));
        setLayout(null);

        int panelWidth = 600;
        int paddingX = 50;
        int labelWidth = panelWidth - 2 * paddingX;
        int y = 30;
        int labelHeight = 30;
        int verticalSpacing = 40;

        // Titlu
        JLabel menuLabel = new JLabel("Istoric");
        menuLabel.setForeground(Color.BLACK);
        menuLabel.setFont(new Font("AvantGarde", Font.BOLD, 38));
        menuLabel.setBounds(paddingX, y, labelWidth, labelHeight + 10);
        add(menuLabel);

        y += labelHeight + verticalSpacing;

        // Numar servicii medicale
        JLabel numarServicii = new JLabel("Numar servicii medicale: " +
                service.getIstoricMedicalService().getNrServicii(pacient.getIstoricMedical()));
        numarServicii.setForeground(Color.BLACK);
        numarServicii.setBounds(paddingX, y, labelWidth, labelHeight);
        add(numarServicii);

        y += labelHeight + 10;

        // Durata medie între consultatii
        JLabel durataMedieServicii = new JLabel("Durata medie intre consultatii: " +
                service.getIstoricMedicalService().timpMediuIntreConsultatii(pacient.getIstoricMedical()));
        durataMedieServicii.setForeground(Color.BLACK);
        durataMedieServicii.setBounds(paddingX, y, labelWidth, labelHeight);
        add(durataMedieServicii);

        y += labelHeight + 10;

        // Afectiuni frecvente
        JLabel afectiuniFrecvente = new JLabel(
                "Afectiuni frecvente: " +
                        service.getIstoricMedicalService().celeMaiFrecventeAfectiuni(pacient.getIstoricMedical()));
        afectiuniFrecvente.setForeground(Color.BLACK);
        afectiuniFrecvente.setBounds(paddingX, y, labelWidth, labelHeight);
        add(afectiuniFrecvente);

        y += labelHeight + 20;

        // Tabel cu istoric servicii
        JTable tabel = new JTable(service.getIstoricMedicalService().istoricServicii(pacient.getIstoricMedical()));
        JScrollPane scrollPane = new JScrollPane(tabel);
        scrollPane.setBounds(paddingX, y, labelWidth, 250); // înălțime ajustată să nu iasă din panou
        add(scrollPane);

        // Buton înapoi
        JButton backButton = new JButton("Inapoi");
        backButton.setBounds(paddingX, 500, 100, 30);
        backButton.addActionListener(e -> cardLayout.show(parentPanel, "PacientiInfoPanel"));
        add(backButton);
    }
}
