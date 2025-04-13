package services.panels;

import entities.Persoana;
import services.services.PersoanaServices;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public abstract class PersoanaListPanel<T extends Persoana> extends JPanel
{
    private JList<T> listaPersoane;
    protected PersoanaServices<T> service;
    private CardLayout cardLayout;
    private JPanel parentPanel;
    protected JButton backButton;
    protected JButton cautaButton;
    protected JTextField numeField;
    protected JTextField prenumeField;

    public PersoanaListPanel(PersoanaServices<T>  service, CardLayout cardLayout, JPanel parentPanel, String titlu)
    {
        this.service = service;
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setLayout(null);

        int y = 20; // variabilă pentru poziția verticală

        // Titlu
        JLabel titleLabel = new JLabel(titlu);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(210, y, 200, 30);
        add(titleLabel);
        y += 50;

        // Secțiune căutare
        JLabel cautareLabel = new JLabel("Căutare:");
        cautareLabel.setBounds(50, y, 70, 30);
        add(cautareLabel);

        JLabel numeLabel = new JLabel("Nume:");
        numeLabel.setBounds(130, y, 50, 30);
        add(numeLabel);

        numeField = new JTextField();
        numeField.setBounds(180, y, 100, 30);
        add(numeField);

        JLabel prenumeLabel = new JLabel("Prenume:");
        prenumeLabel.setBounds(290, y, 70, 30);
        add(prenumeLabel);

        prenumeField = new JTextField();
        prenumeField.setBounds(360, y, 100, 30);
        add(prenumeField);

        cautaButton = new JButton("Caută");
        cautaButton.setBounds(470, y, 80, 30);
        cautaButton.addActionListener(e -> cautaPersoane(numeField.getText(), prenumeField.getText()));
        add(cautaButton);

        y+= 50;

        // Lista de persoane
        listaPersoane = new JList<>();

        listaPersoane.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            String textAfisat = value.getId() + " - " + value.getNume() + " " + value.getPrenume();
            JLabel label = new JLabel(textAfisat);
            if (isSelected) {
                label.setBackground(list.getSelectionBackground());
                label.setForeground(list.getSelectionForeground());
                label.setOpaque(true);
            }
            return label;
        });

        JScrollPane scrollPane = new JScrollPane(listaPersoane);
        scrollPane.setBackground(Color.decode("#B0E0E6"));
        scrollPane.setBounds(50, y, 500, 400);
        add(scrollPane);
        y += 420;

        listaPersoane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    T persoana = listaPersoane.getSelectedValue();
                    if (persoana != null) {
                        showPersoanaInfo(persoana);
                    }
                }
            }
        });

        JButton refreshButton = new JButton("Actualizează");
        refreshButton.addActionListener(e -> incarcaPersoane());
        refreshButton.setBounds(125, y, 150, 30);
        add(refreshButton);

        backButton = new JButton("Înapoi");
        backButton.setBounds(325, y, 150, 30);
        add(backButton);

        incarcaPersoane();
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
