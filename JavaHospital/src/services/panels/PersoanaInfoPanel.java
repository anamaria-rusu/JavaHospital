package services.panels;

import entities.Persoana;
import javax.swing.*;
import java.awt.*;

public abstract class PersoanaInfoPanel extends JPanel {

    protected JButton backButton;

    public PersoanaInfoPanel(Persoana persoana, String titlu) {
        setBackground(Color.decode("#B0E0E6"));
        setLayout(null);

        // Titlu
        JLabel titleLabel = new JLabel(titlu);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(50, 20, 300, 30);
        add(titleLabel);

        // Nume
        JLabel numeLabel = new JLabel("Nume:");
        numeLabel.setBounds(50, 70, 100, 30);
        add(numeLabel);

        JLabel numePersoana = new JLabel(persoana.getNume());
        numePersoana.setBounds(160, 70, 200, 30);
        add(numePersoana);

        // Prenume
        JLabel prenumeLabel = new JLabel("Prenume:");
        prenumeLabel.setBounds(50, 110, 100, 30);
        add(prenumeLabel);

        JLabel prenumePersoana = new JLabel(persoana.getPrenume());
        prenumePersoana.setBounds(160, 110, 200, 30);
        add(prenumePersoana);

        // Data nașterii
        JLabel dataNasteriiLabel = new JLabel("Data nașterii:");
        dataNasteriiLabel.setBounds(50, 150, 100, 30);
        add(dataNasteriiLabel);

        JLabel dataNasterii = new JLabel(persoana.getDataNasterii().toString());
        dataNasterii.setBounds(160, 150, 200, 30);
        add(dataNasterii);

        // Telefon
        JLabel telefonLabel = new JLabel("Telefon:");
        telefonLabel.setBounds(50, 190, 100, 30);
        add(telefonLabel);

        JLabel telefonPersoana = new JLabel(persoana.getTelefon());
        telefonPersoana.setBounds(160, 190, 200, 30);
        add(telefonPersoana);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 230, 100, 30);
        add(emailLabel);

        JLabel emailPersoana = new JLabel(persoana.getEmail());
        emailPersoana.setBounds(160, 230, 200, 30);
        add(emailPersoana);

        // Buton pentru întoarcere
        backButton = new JButton("Inapoi");
        backButton.setBounds(50, 280, 100, 30);
        add(backButton);
    }

    //configurarea corecta a butonului de back
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

    // Metodă abstractă pentru informații specifice (va fi implementată în clasele copil)
    protected abstract void afiseazaInformatiiSpecifice();
}
