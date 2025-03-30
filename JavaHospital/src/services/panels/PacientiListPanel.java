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
        setLayout(new BorderLayout());

        // Titlu
        JLabel titleLabel = new JLabel("Lista Pacienti");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Lista pacienților
        listaPacienti = new JList<>();
        JScrollPane scrollPane = new JScrollPane(listaPacienti);
        add(scrollPane, BorderLayout.CENTER);

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
        add(refreshButton, BorderLayout.SOUTH);

        // Buton pentru întoarcere la panelul anterior
        JButton backButton = new JButton("Înapoi");
        backButton.addActionListener(e -> cardLayout.show(parentPanel, "PacientiPanel"));
        add(backButton, BorderLayout.WEST);

        // Încărcăm pacienții la inițializare
        incarcaPacienti();
    }

    // Metodă simplificată pentru încărcarea pacienților
    private void incarcaPacienti()
    {
        listaPacienti.setListData(service.getPacienti().toArray(new Pacient[0]));
    }

    // Metodă pentru afișarea detaliilor despre pacient
    private void showPacientInfo(Pacient pacient)
    {
        // Creăm un panel nou pentru info despre pacient
        PacientiInfoPanel pacientInfoPanel = new PacientiInfoPanel(pacient);

        // Adăugăm panelul la containerul părinte (folosind CardLayout)
        parentPanel.add(pacientInfoPanel, "PacientiInfoPanel");

        // Navigăm către panelul nou
        cardLayout.show(parentPanel, "PacientiInfoPanel");
    }
}
