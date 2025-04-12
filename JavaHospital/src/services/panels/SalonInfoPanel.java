package services.panels;

import entities.Internare;
import entities.Pacient;
import entities.Salon;
import services.services.InternareServices;
import services.services.Services;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class SalonInfoPanel extends JPanel {
    Services services;
    CardLayout cardLayout;
    JPanel parentPanel;
    JList<Internare> listaPacienti;

    public SalonInfoPanel(Salon salon, CardLayout cardLayout, JPanel parentPanel,Services services) {
        this.services = services;
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setBackground(Color.decode("#9E9765"));
        setLayout(null);

        // Titlu
        JLabel titleLabel = new JLabel("Informatii Salon");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(50, 20, 300, 30);
        add(titleLabel);

        // Locatie
        JLabel locatieLabel = new JLabel("Locatie:");
        locatieLabel.setBounds(50, 70, 100, 30);
        add(locatieLabel);

        JLabel locatie = new JLabel(salon.getLocatie());
        locatie.setBounds(160, 70, 200, 30);
        add(locatie);

        // capacitate
        JLabel capacitateLabel = new JLabel("Capacitate:");
        capacitateLabel.setBounds(50, 110, 100, 30);
        add(capacitateLabel);

        JLabel capacitate = new JLabel(salon.getCapacitateMaxima()+ "/"+ salon.getCapacitateCurenta());
        capacitate.setBounds(160, 110, 200, 30);
        add(capacitate);


        // pacienti
        JLabel pacienti = new JLabel("Fise Medicale Pacienti");
        capacitateLabel.setBounds(50, 120, 100, 30);
        add(capacitateLabel);


        listaPacienti = new JList<>();
        JScrollPane scrollPane = new JScrollPane(listaPacienti);
        scrollPane.setBackground(Color.decode("#B0E0E6"));
        scrollPane.setBounds(100,150 , 400, 400);
        add(scrollPane);


        listaPacienti.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    Internare internare = listaPacienti.getSelectedValue();
                    if (internare != null) {
                        showFisaInternareInfo(internare);
                    }
                }
            }
        });

        // Buton pentru actualizare
        JButton refreshButton = new JButton("Actualizează");
        refreshButton.addActionListener(e -> incarcaPacienti(salon));
        refreshButton.setBounds(125, 570, 150, 30);
        add(refreshButton);

        JButton backButton = new JButton("Inapoi");
        backButton.addActionListener(e -> cardLayout.show(parentPanel, "SaloanePanel"));
        backButton.setBounds(325, 570, 150, 30);
        add(backButton);

        incarcaPacienti(salon);
    }


    private void incarcaPacienti(Salon salon) {
        List<Internare> persoane = salon.getPacientiInternati();
        listaPacienti.setListData(persoane.toArray((Internare[]) new Internare[0]));
    }

    protected void showFisaInternareInfo(Internare internare) {
        InternareInfoPanel intInfoPanel = new InternareInfoPanel(internare,"SalonInfoPanel", cardLayout, parentPanel,services);
        parentPanel.add(intInfoPanel, "InternareInfoPanel");
        cardLayout.show(parentPanel, "InternareInfoPanel");
    }
}


