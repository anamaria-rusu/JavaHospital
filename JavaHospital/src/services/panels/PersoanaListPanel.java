package services.panels;

import entities.Persoana;
import services.services.PersoanaServices;
import services.services.Services;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public abstract class PersoanaListPanel<T extends Persoana> extends JPanel {
    private JList<T> listaPersoane;
    protected PersoanaServices<T> service;
    private CardLayout cardLayout;
    private JPanel parentPanel;
    protected JButton backButton;
    protected JButton cautaButton;

    protected JTextField numeField;
    protected JTextField prenumeField;

    public PersoanaListPanel(PersoanaServices<T>  service, CardLayout cardLayout, JPanel parentPanel, String titlu) {
        this.service = service;
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setBackground(Color.decode("#B0E0E6"));
        setLayout(null);

        // Titlu
        JLabel titleLabel = new JLabel(titlu);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(210, 20, 200, 30);
        add(titleLabel);

        // Lista de persoane
        listaPersoane = new JList<>();
        JScrollPane scrollPane = new JScrollPane(listaPersoane);
        scrollPane.setBackground(Color.decode("#B0E0E6"));
        scrollPane.setBounds(50, 60, 500, 400);
        add(scrollPane);

        // Detectăm click-urile pe listă
        listaPersoane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Click simplu
                    T persoana = listaPersoane.getSelectedValue();
                    if (persoana != null) {
                        showPersoanaInfo(persoana);
                    }
                }
            }
        });

        // Buton pentru actualizare
        JButton refreshButton = new JButton("Actualizează");
        refreshButton.addActionListener(e -> incarcaPersoane());
        refreshButton.setBounds(125, 500, 150, 30);
        add(refreshButton);

        // Buton pentru întoarcere
        backButton = new JButton("Înapoi");
        //backButton.addActionListener(e -> cardLayout.show(parentPanel, "PersoanePanel"));
        backButton.setBounds(325, 500, 150, 30);
        add(backButton);

        // Încărcăm persoanele la inițializare
        incarcaPersoane();








        //motor cautare

        numeField = new JTextField();
        numeField.setBounds(150, 550, 150, 30);
        add(numeField);

        prenumeField = new JTextField();
        prenumeField.setBounds(350, 550, 150, 30);
        add(prenumeField);


        cautaButton = new JButton("cauta");
        cautaButton.addActionListener(e -> cautaPersoane(numeField.getText(),prenumeField.getText()));
        cautaButton.setBounds(125, 530, 150, 30);
        add(cautaButton);



    }

    protected void setBackButton(String panelName)
    {
        backButton.addActionListener(e -> {
            Container parent = getParent();
            if (parent != null && parent.getLayout() instanceof CardLayout) {
                CardLayout cardLayout = (CardLayout) parent.getLayout();
                cardLayout.show(parent, panelName);
            }
        });
    }


    private void incarcaPersoane() {
        List<T> persoane = service.getPersoane();
        listaPersoane.setListData(persoane.toArray((T[]) new Persoana[0]));
    }

    private void cautaPersoane(String nume, String prenume){
        List<T> persoane = service.cautaPersoane(nume,prenume);
        listaPersoane.setListData(persoane.toArray((T[]) new Persoana[0]));
    }

    // Metodă abstractă care va fi implementată în clasele copil
    protected abstract void showPersoanaInfo(T persoana);

    // Adăugăm metodele getter pentru a putea fi accesate în subclase
    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getParentPanel() {
        return parentPanel;
    }
}
