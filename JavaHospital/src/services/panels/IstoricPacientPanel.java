package services.panels;

import entities.Pacient;
import entities.Salon;
import services.services.Services;

import javax.swing.*;
import java.awt.*;

public class IstoricPacientPanel extends JPanel{
    private Services services;
    private CardLayout cardLayout;
    private JPanel parentPanel;

    public IstoricPacientPanel(Pacient pacient, CardLayout cardLayout, JPanel parentPanel, Services service) {
        this.services = services;
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setBackground(Color.decode("#9E9765"));
        setLayout(null);

        int y = 30;
        JLabel menuLabel = new JLabel("Istoric");
        menuLabel.setForeground(Color.WHITE);
        menuLabel.setFont(new Font("AvantGarde", Font.BOLD, 38));
        menuLabel.setBounds(100, y, 400, 40);
        add(menuLabel);

        y+=60;

        JLabel numarServicii = new JLabel ("Numar servicii medicale: " + service.getIstoricMedicalService().getNrServicii(pacient.getIstoricMedical()));
        numarServicii.setForeground(Color.WHITE);
        numarServicii.setBounds(100, y, 400, 40);
        add(numarServicii);

        y+=50;

        JLabel durataMedieServicii = new JLabel ("Drata medie intre consultatii: " + service.getIstoricMedicalService().timpMediuIntreConsultatii(pacient.getIstoricMedical()));
        durataMedieServicii.setForeground(Color.WHITE);
        durataMedieServicii.setBounds(100, y, 400, 40);
        add(durataMedieServicii);

        y+=50;

        JLabel afectiuniFrecvente = new JLabel (service.getIstoricMedicalService().celeMaiFrecventeAfectiuni(pacient.getIstoricMedical()));
        afectiuniFrecvente.setForeground(Color.WHITE);
        afectiuniFrecvente.setBounds(100, y, 400, 40);
        add(afectiuniFrecvente);

        y+=50;

        JTable tabel = new JTable(service.getIstoricMedicalService().istoricServicii(pacient.getIstoricMedical()));
        JScrollPane scrollPane = new JScrollPane(tabel);
        scrollPane.setBackground(Color.decode("#B0E0E6"));
        scrollPane.setBounds(50, y, 500, 400);
        add(scrollPane);

        y = 550;
       JButton backButton = new JButton("Inapoi");
       backButton.setBounds(100,y,100,30);
       backButton.addActionListener(e->cardLayout.show(parentPanel,"PacientiInfoPanel"));
       add(backButton);




    }
}
