package services.panels;

import entities.Pacient;
import services.services.PacientiServices;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PacientiListPanel extends JPanel {
    private JList<Pacient> listaPacienti;
    private PacientiServices service;
    private CardLayout cardLayout;
    private JPanel parentPanel;

    public PacientiListPanel(PacientiServices service, CardLayout cardLayout, JPanel parentPanel) {
        this.service = service;
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setBackground(Color.decode("#B0E0E6"));
        setLayout(null); // Folosim setLayout(null) pentru a folosi setBounds

        // Titlu
        JLabel titleLabel = new JLabel("Lista Pacienti");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(210, 20, 200, 30); // Titlul sus
        add(titleLabel);

        // Lista pacienților
        listaPacienti = new JList<>();
        JScrollPane scrollPane = new JScrollPane(listaPacienti);
        scrollPane.setBackground(Color.decode("#B0E0E6"));
        scrollPane.setBounds(50, 60, 500, 500); // Panoul de scroll la centru
        add(scrollPane);

        // Adăugăm un MouseAdapter pentru a detecta click-urile pe listă
        listaPacienti.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Click simplu
                    Pacient pacient = listaPacienti.getSelectedValue();
                    if (pacient != null) {
                        showPacientInfo(pacient);
                    }
                }
            }
        });

        // Buton pentru actualizare
        JButton refreshButton = new JButton("Actualizează");
        refreshButton.addActionListener(e -> incarcaPacienti());
        refreshButton.setBounds(125, 570, 150, 30); // Butonul de actualizare jos la centru
        add(refreshButton);

        // Buton pentru întoarcere la panelul anterior
        JButton backButton = new JButton("Înapoi");
        backButton.addActionListener(e -> cardLayout.show(parentPanel, "PacientiPanel"));
        backButton.setBounds(325, 570, 150, 30); // Butonul de întoarcere jos la centru
        add(backButton);

        // Încărcăm pacienții la inițializare
        incarcaPacienti();
    }

    // Metodă simplificată pentru încărcarea pacienților
    private void incarcaPacienti() {
        listaPacienti.setListData(service.getPacienti().toArray(new Pacient[0]));
    }

    // Metodă pentru afișarea detaliilor despre pacient
    private void showPacientInfo(Pacient pacient) {
        // Creăm un panel nou pentru info despre pacient
        PacientiInfoPanel pacientInfoPanel = new PacientiInfoPanel(pacient);

        // Adăugăm panelul la containerul părinte (folosind CardLayout)
        parentPanel.add(pacientInfoPanel, "PacientiInfoPanel");

        // Navigăm către panelul nou
        cardLayout.show(parentPanel, "PacientiInfoPanel");
    }
}
