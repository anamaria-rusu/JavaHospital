package services.panels;

import com.toedter.calendar.JDateChooser;
import services.services.Services;
import entities.*;
import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Set;


public class InternareAddPanel extends JPanel {
    private Services services;
    private CardLayout cardLayout;
    private JPanel parentPanel;
    private Pacient pacient;
    private Salon salon;
    private JTextField idPacient;
    private JLabel pacientInfoLabel;
    private JTextField diagnosticField;
    private JLabel salonField;
    private JDateChooser dateAngajareChooser;
    private JComboBox departamentComboBox;

    public InternareAddPanel(Services services, CardLayout cardLayout, JPanel parentPanel)
    {
        this.services = services;
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setBackground(Color.decode("#e6e2b0"));
        setLayout(null);

        // Dimensiuni utile pentru controlul layout-ului
        int labelX = 80;
        int fieldX = 250;
        int rightX = 470;
        int fullWidth = 200;
        int height = 30;
        int spacing = 50;

        int y = 40;

// Titlu centrat
        JLabel titleLabel = new JLabel("Internare");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(200, y, 200, 30);
        add(titleLabel);

        y += 60;

// Selectare pacient
        JLabel pacientLabel = new JLabel("Selectează pacient (ID):");
        pacientLabel.setBounds(labelX, y, 180, height);
        add(pacientLabel);

        idPacient = new JTextField();
        idPacient.setBounds(fieldX, y, fullWidth, height);
        add(idPacient);

        JButton cautaButton = new JButton("Caută");
        cautaButton.setBounds(rightX, y, 100, height);
        add(cautaButton);

        // Eveniment pentru butonul „Caută”
        cautaButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idPacient.getText()); // Citește ID-ul
                pacient = services.getPacientiServices().cautaEntitate(id); // Caută pacientul

                if (pacient != null)
                    pacientInfoLabel.setText("Pacient: " + pacient.getNume() + " " + pacient.getPrenume());
                else
                    pacientInfoLabel.setText("Pacientul nu a fost găsit!");
            } catch (NumberFormatException ex) {
                pacientInfoLabel.setText("ID invalid! Introdu un număr.");
            }
        });

        y += spacing;

        pacientInfoLabel = new JLabel("Detalii pacient: ");
        pacientInfoLabel.setBounds(labelX, y, 350, height);
        add(pacientInfoLabel);

        JButton detaliiPacient = new JButton("Detalii");
        detaliiPacient.setBounds(rightX, y, 100, height);
        add(detaliiPacient);

        y += spacing;

// Departament
        JLabel departamentLabel = new JLabel("Departament:");
        departamentLabel.setBounds(labelX, y, 150, height);
        add(departamentLabel);

        departamentComboBox = new JComboBox<>();
        departamentComboBox.setBounds(fieldX, y, fullWidth, height);
        add(departamentComboBox);

        getDepartamente();

        y += spacing;

// Diagnostic
        JLabel diagnosticLabel = new JLabel("Diagnostic:");
        diagnosticLabel.setBounds(labelX, y, 150, height);
        add(diagnosticLabel);

        diagnosticField = new JTextField();
        diagnosticField.setBounds(fieldX, y, fullWidth, height);
        add(diagnosticField);

        y += spacing;

// Salon
        JLabel salonLabel = new JLabel("Salon:");
        salonLabel.setBounds(labelX, y, 150, height);
        add(salonLabel);

        salonField = new JLabel();
        salonField.setBounds(fieldX, y, fullWidth, height);
        add(salonField);

        y += spacing;

// Verificare disponibilitate
        JButton refreshButton = new JButton("Verifică Disponibilitate");
        refreshButton.setBounds(fieldX, y, fullWidth, height);
        refreshButton.addActionListener(e -> verificaDisponibilitate());
        add(refreshButton);

        y += spacing;

// Adaugă Internare
        JButton adaugaButton = new JButton("Adaugă Internare");
        adaugaButton.setBounds(fieldX, y, fullWidth, height);
        adaugaButton.addActionListener(e -> adaugaInternare());
        add(adaugaButton);

        y += spacing;

// Înapoi
        JButton backButton = new JButton("Înapoi");
        backButton.setBounds(fieldX, y, fullWidth, height);
        backButton.addActionListener(e -> cardLayout.show(parentPanel, "InternarePanel"));
        add(backButton);
    }

    private void verificaDisponibilitate() {
        // salonul disponibil care are cei mai puțini pacienți
        salon = services.getSalonServices().verificaDisponibilitate();
        salonField.setText(String.valueOf(salon.getId()));
    }

    public void adaugaInternare() {
        String diagnostic = diagnosticField.getText();
        String departament = (String) departamentComboBox.getSelectedItem();

        if (!diagnostic.isEmpty() && !idPacient.getText().isEmpty() && salon != null && departament != null) {
            Internare internareNoua = services.getInternareServices().internarePacient(pacient, salon, diagnostic, departament);
            JOptionPane.showMessageDialog(this, "Internare adăugată cu succes!");
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Introduceți toate datele!");
        }
    }

    public void clearFields() {
        salonField.setText("");
        diagnosticField.setText(""); // Setează câmpul de diagnostic la gol
        pacientInfoLabel.setText("");
        idPacient.setText("");
        departamentComboBox.setSelectedIndex(-1);
    }

    private void getDepartamente() {

        Set<String> departamente = services.getDepartamente();
        for (String departament : departamente) {
            departamentComboBox.addItem(departament);
        }
    }
}
