package services.panels;

import services.services.PacientiServices;
import javax.swing.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PacientiAddPanel extends JPanel
{
    private PacientiServices service;
    private CardLayout cardLayout;
    private JPanel parentPanel;

    public PacientiAddPanel(PacientiServices service, CardLayout cardLayout, JPanel parentPanel)
    {
        this.service = service;
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setBackground(Color.decode("#B0E0E6"));
        setLayout(null);

        JLabel titleLabel = new JLabel("Adaugă Pacient");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(150, 20, 200, 30);
        add(titleLabel);

        JLabel numeLabel = new JLabel("Nume:");
        numeLabel.setBounds(50, 70, 100, 30);
        add(numeLabel);

        JTextField numeField = new JTextField();
        numeField.setBounds(150, 70, 200, 30);
        add(numeField);

        JLabel prenumeLabel = new JLabel("Prenume:");
        prenumeLabel.setBounds(50, 110, 100, 30);
        add(prenumeLabel);

        JTextField prenumeField = new JTextField();
        prenumeField.setBounds(150, 110, 200, 30);
        add(prenumeField);

        JLabel dataNasteriiLabel = new JLabel("Data nașterii:");
        dataNasteriiLabel.setBounds(50, 150, 100, 30);
        add(dataNasteriiLabel);

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd-MM-yyyy");
        dateChooser.setBounds(150, 150, 200, 30);
        add(dateChooser);

        JLabel telefonLabel = new JLabel("Telefon:");
        telefonLabel.setBounds(50, 190, 100, 30);
        add(telefonLabel);

        JTextField telefonField = new JTextField();
        telefonField.setBounds(150, 190, 200, 30);
        add(telefonField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 230, 100, 30);
        add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(150, 230, 200, 30);
        add(emailField);



        JButton adaugaButton = new JButton("Adaugă Pacient");
        adaugaButton.setBounds(150, 280, 150, 30);
        adaugaButton.addActionListener(e -> {
            String nume = numeField.getText();
            String prenume = prenumeField.getText();
            java.util.Date dataSelectata = dateChooser.getDate();
            String telefon = telefonField.getText();
            String email = emailField.getText();

            if (!nume.isEmpty() && !prenume.isEmpty() && dataSelectata != null && !telefon.isEmpty() && !email.isEmpty()) {
                // Convertește în LocalDate
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String dataFormatata = dateFormat.format(dataSelectata);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate localDate = LocalDate.parse(dataFormatata, formatter);

                service.adaugaPacient(nume, prenume, localDate, telefon, email);
                JOptionPane.showMessageDialog(this, "Pacient adăugat cu succes!");

                // Resetează câmpurile
                numeField.setText("");
                prenumeField.setText("");
                dateChooser.setDate(null);
                telefonField.setText("");
                emailField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Introduceți toate datele!");
            }
        });
        add(adaugaButton);

        // 🔙 Buton pentru întoarcere la `PacientiPanel`
        JButton backButton = new JButton("Înapoi");
        backButton.setBounds(150, 320, 150, 30);
        backButton.addActionListener(e -> cardLayout.show(parentPanel, "PacientiPanel"));
        add(backButton);
    }
}
