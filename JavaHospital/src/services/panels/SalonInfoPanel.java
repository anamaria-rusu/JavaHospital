package services.panels;

import entities.Internare;
import entities.Salon;
import services.services.InternareServices;
import services.services.SalonServices;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class SalonInfoPanel extends JPanel {
    CardLayout cardLayout;
    JPanel parentPanel;
    JList<Internare> listaPacienti;

    public SalonInfoPanel(Salon salon, CardLayout cardLayout, JPanel parentPanel) {
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setBackground(Color.decode("#e6e2b0"));
        setLayout(null);

        // Titlu
        JLabel titleLabel = new JLabel("Informații Salon");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBounds(180, 20, 300, 40);
        add(titleLabel);

        // Locatie
        JLabel locatieLabel = new JLabel("Locație:");
        locatieLabel.setBounds(50, 80, 100, 25);
        add(locatieLabel);

        JLabel locatie = new JLabel(salon.getLocatie());
        locatie.setBounds(160, 80, 300, 25);
        add(locatie);

        // Capacitate
        JLabel capacitateLabel = new JLabel("Capacitate:");
        capacitateLabel.setBounds(50, 120, 100, 25);
        add(capacitateLabel);

        JLabel capacitate = new JLabel(salon.getCapacitateMaxima() + " / " + SalonServices.getSalonServices().getCapacitateCurenta(salon));
        capacitate.setBounds(160, 120, 200, 25);
        add(capacitate);

        // Etichetă pacienți
        JLabel pacientiLabel = new JLabel("Fișe medicale pacienți internați:");
        pacientiLabel.setBounds(50, 160, 300, 25);
        add(pacientiLabel);

        // Listă pacienți
        listaPacienti = new JList<>();

        listaPacienti.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            String textAfisat = value.getPacient().getId() + " - " + value.getPacient().getNume() + " " + value.getPacient().getPrenume();
            JLabel label = new JLabel(textAfisat);
            if (isSelected) {
                label.setBackground(list.getSelectionBackground());
                label.setForeground(list.getSelectionForeground());
                label.setOpaque(true);
            }
            return label;
        });

        JScrollPane scrollPane = new JScrollPane(listaPacienti);
        scrollPane.setBounds(50, 200, 500, 300);
        add(scrollPane);

        // Mouse click pe listă
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

        // Buton actualizare
        JButton refreshButton = new JButton("Actualizează");
        refreshButton.setBounds(130, 530, 150, 35);
        add(refreshButton);
        refreshButton.addActionListener(e -> incarcaPacienti(salon));

        // Buton înapoi
        JButton backButton = new JButton("Înapoi");
        backButton.setBounds(310, 530, 150, 35);
        add(backButton);
        backButton.addActionListener(e -> cardLayout.show(parentPanel, "SaloanePanel"));

        incarcaPacienti(salon);
    }


    private void incarcaPacienti(Salon salon) {
        List<Internare> persoane = InternareServices.getInternareServices().getPacientiInternati(salon);
        listaPacienti.setListData(persoane.toArray((Internare[]) new Internare[0]));
    }

    protected void showFisaInternareInfo(Internare internare) {
        InternareInfoPanel intInfoPanel = new InternareInfoPanel(internare,"SalonInfoPanel", cardLayout, parentPanel);
        parentPanel.add(intInfoPanel, "InternareInfoPanel");
        cardLayout.show(parentPanel, "InternareInfoPanel");
    }
}


