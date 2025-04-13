package services.panels;

import entities.Persoana;
import javax.swing.*;
import java.awt.*;

public abstract class PersoanaInfoPanel extends JPanel {

    protected JButton backButton;

    public PersoanaInfoPanel(Persoana persoana, String titlu) {

        setLayout(null);

        int y = 20;

        // Titlu
        JLabel titleLabel = new JLabel(titlu);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titleLabel.setBounds(50, y, 300, 30);
        add(titleLabel);

        y += 50;

        // Nume
        JLabel numeLabel = new JLabel("Nume:");
        numeLabel.setBounds(50, y, 100, 30);
        add(numeLabel);

        JLabel numePersoana = new JLabel(persoana.getNume());
        numePersoana.setBounds(160, y, 200, 30);
        add(numePersoana);

        y += 40;

        // Prenume
        JLabel prenumeLabel = new JLabel("Prenume:");
        prenumeLabel.setBounds(50, y, 100, 30);
        add(prenumeLabel);

        JLabel prenumePersoana = new JLabel(persoana.getPrenume());
        prenumePersoana.setBounds(160, y, 200, 30);
        add(prenumePersoana);

        y += 40;

        // Data nașterii
        JLabel dataNasteriiLabel = new JLabel("Data nașterii:");
        dataNasteriiLabel.setBounds(50, y, 100, 30);
        add(dataNasteriiLabel);

        JLabel dataNasterii = new JLabel(persoana.getDataNasterii().toString());
        dataNasterii.setBounds(160, y, 200, 30);
        add(dataNasterii);

        y += 40;

        // Telefon
        JLabel telefonLabel = new JLabel("Telefon:");
        telefonLabel.setBounds(50, y, 100, 30);
        add(telefonLabel);

        JLabel telefonPersoana = new JLabel(persoana.getTelefon());
        telefonPersoana.setBounds(160, y, 200, 30);
        add(telefonPersoana);

        y += 40;

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, y, 100, 30);
        add(emailLabel);

        JLabel emailPersoana = new JLabel(persoana.getEmail());
        emailPersoana.setBounds(160, y, 200, 30);
        add(emailPersoana);

        y += 50;

        // Buton pentru întoarcere
        backButton = new JButton("Înapoi");
        backButton.setBounds(50, y, 100, 30);
        add(backButton);
    }

    // Configurarea corectă a butonului de back
    protected void setBackButton(String panelName) {
        backButton.addActionListener(e -> {
            Container parent = getParent();
            if (parent != null && parent.getLayout() instanceof CardLayout) {
                CardLayout cardLayout = (CardLayout) parent.getLayout();
                cardLayout.show(parent, panelName);
            }
        });
    }

    // Metodă abstractă pentru informații specifice
    protected abstract void afiseazaInformatiiSpecifice();
}
