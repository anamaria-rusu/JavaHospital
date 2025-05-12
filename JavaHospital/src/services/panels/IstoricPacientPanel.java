package services.panels;

import entities.IstoricPacient;
import entities.Pacient;
import services.services.*;

import javax.swing.*;
import java.awt.*;

public class IstoricPacientPanel extends JPanel
{
    private CardLayout cardLayout;
    private JPanel parentPanel;
    private IstoricPacient istoricMedical;

    public IstoricPacientPanel(Pacient pacient, CardLayout cardLayout, JPanel parentPanel) {
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;
        this.istoricMedical = IstoricMedicalServices.getIstoricMedicalServices().incarcaDateIstoricMedical(pacient.getId());

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
                IstoricMedicalServices.getIstoricMedicalServices().getNrServicii(istoricMedical));
        numarServicii.setForeground(Color.BLACK);
        numarServicii.setBounds(paddingX, y, labelWidth, labelHeight);
        add(numarServicii);

        y += labelHeight + 10;

        // Durata medie între consultatii
        JLabel durataMedieServicii = new JLabel("Durata medie intre consultatii: " +
                IstoricMedicalServices.getIstoricMedicalServices().timpMediuIntreConsultatii(istoricMedical));
        durataMedieServicii.setForeground(Color.BLACK);
        durataMedieServicii.setBounds(paddingX, y, labelWidth, labelHeight);
        add(durataMedieServicii);

        y += labelHeight + 10;

        JLabel afectiuniFrecvente = new JLabel(
                "Afectiuni frecvente: " +
                        IstoricMedicalServices.getIstoricMedicalServices().celeMaiFrecventeAfectiuni(istoricMedical));
        afectiuniFrecvente.setForeground(Color.BLACK);
        afectiuniFrecvente.setBounds(paddingX, y, labelWidth, labelHeight);
        add(afectiuniFrecvente);

        y += labelHeight + 20;

        JTable tabel = new JTable(IstoricMedicalServices.getIstoricMedicalServices().istoricServicii(istoricMedical));
        JScrollPane scrollPane = new JScrollPane(tabel);
        scrollPane.setBounds(paddingX, y, labelWidth, 250);
        add(scrollPane);

        JButton backButton = new JButton("Inapoi");
        backButton.setBounds(paddingX, 500, 100, 30);
        backButton.addActionListener(e -> cardLayout.show(parentPanel, "PacientiInfoPanel"));
        add(backButton);
    }
}
