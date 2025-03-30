package services.panels;

import entities.Pacient;
import javax.swing.*;
import java.awt.*;

public class PacientiInfoPanel extends JPanel {

    public PacientiInfoPanel(Pacient pacient) {
        setBackground(Color.decode("#B0E0E6"));
        setLayout(null);

        // Titlu
        JLabel titleLabel = new JLabel("Informații Pacient");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(50, 20, 300, 30);
        add(titleLabel);

        // Nume
        JLabel numeLabel = new JLabel("Nume:");
        numeLabel.setBounds(50, 70, 100, 30);
        add(numeLabel);

        JLabel numePacient = new JLabel(pacient.getNume());
        numePacient.setBounds(160, 70, 200, 30);
        add(numePacient);

        // Prenume
        JLabel prenumeLabel = new JLabel("Prenume:");
        prenumeLabel.setBounds(50, 110, 100, 30);
        add(prenumeLabel);

        JLabel prenumePacient = new JLabel(pacient.getPrenume());
        prenumePacient.setBounds(160, 110, 200, 30);
        add(prenumePacient);

        // Data nașterii
        JLabel dataNasteriiLabel = new JLabel("Data nașterii:");
        dataNasteriiLabel.setBounds(50, 150, 100, 30);
        add(dataNasteriiLabel);

        JLabel dataNasterii = new JLabel(pacient.getDataNasterii().toString());
        dataNasterii.setBounds(160, 150, 200, 30);
        add(dataNasterii);

        // Telefon
        JLabel telefonLabel = new JLabel("Telefon:");
        telefonLabel.setBounds(50, 190, 100, 30);
        add(telefonLabel);

        JLabel telefonPacient = new JLabel(pacient.getTelefon());
        telefonPacient.setBounds(160, 190, 200, 30);
        add(telefonPacient);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 230, 100, 30);
        add(emailLabel);

        JLabel emailPacient = new JLabel(pacient.getEmail());
        emailPacient.setBounds(160, 230, 200, 30);
        add(emailPacient);


        // Buton pentru întoarcere
        JButton backButton = new JButton("Inapoi");
        backButton.setBounds(50, 280, 100, 30);
        backButton.addActionListener(e -> {
            // Navigare înapoi la panelul anterior
            Container parent = getParent();
            if (parent != null && parent.getLayout() instanceof CardLayout) {
                CardLayout cardLayout = (CardLayout) parent.getLayout();
                cardLayout.show(parent, "AfiseazaPacienti");
            }
        });
        add(backButton);
    }
}
