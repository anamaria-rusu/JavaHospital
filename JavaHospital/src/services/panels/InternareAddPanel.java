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


    public InternareAddPanel(Services services, CardLayout cardLayout, JPanel parentPanel) {
        this.services = services;
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setBackground(Color.decode("#9E9765"));
        setLayout(null);

        // Titlu
        JLabel titleLabel = new JLabel("Internare");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(120, 20, 300, 30);
        add(titleLabel);

        int y = 80;

        // Selectare pacient
        JLabel pacientLabel = new JLabel("Selecteaza pacient (ID):");
        pacientLabel.setBounds(50, y, 150, 30);
        add(pacientLabel);

        idPacient = new JTextField();
        idPacient.setBounds(230, y, 100, 30);
        add(idPacient);

        JButton cautaButton = new JButton("Caută");
        cautaButton.setBounds(340, y, 100, 30);
        add(cautaButton);

        pacientInfoLabel = new JLabel("Detalii pacient: ");
        pacientInfoLabel.setBounds(50, y + 40, 400, 30);
        add(pacientInfoLabel);

        JButton detaliiPacient = new JButton("Detalii");
        detaliiPacient.setBounds(450,y,100,30);
        add(detaliiPacient);

        // Eveniment pentru butonul „Caută”
        cautaButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idPacient.getText()); // Citește ID-ul
                pacient = services.getPacientiServices().cautaPacient(id); // Caută pacientul

                if (pacient != null)
                    pacientInfoLabel.setText("Pacient: " + pacient.getNume() + " " + pacient.getPrenume());
                else
                    pacientInfoLabel.setText("Pacientul nu a fost găsit!");
            } catch (NumberFormatException ex) {
                pacientInfoLabel.setText("ID invalid! Introdu un număr.");
            }
        });

        y+=50;


        JLabel departamentLabel = new JLabel("Departament:");
        departamentLabel.setBounds(50, 310, 100, 30);
        add(departamentLabel);


        departamentComboBox = new JComboBox<>();
        departamentComboBox.setBounds(150, 310, 200, 30);
        add(departamentComboBox);

        getDepartamente();

        y+=50;

        // diagnostic
        JLabel diagnosticLabel = new JLabel("Diagnostic");
        diagnosticLabel.setBounds(50, y, 150, 30);
        add(diagnosticLabel);

        diagnosticField = new JTextField();
        diagnosticField.setBounds(230, y, 200, 30);
        add(diagnosticField);

        y+=50;


        // salon
        JLabel salonLabel = new JLabel("Diagnostic");
        salonLabel.setBounds(50, y, 150, 30);
        add(salonLabel);

        salonField = new JLabel();
        salonField.setBounds(230, y, 200, 30);
        add(salonField);

        y+=50;
        // Buton verificare disponibilitate
        JButton refreshButton = new JButton("Verifică Disponibilitate");
        refreshButton.addActionListener(e -> verificaDisponibilitate());
        refreshButton.setBounds(125, y, 200, 30);
        add(refreshButton);

        y += 50;

        // Buton de adăugare consultație
        JButton adaugaButton = new JButton("Adaugă Internare");
        adaugaButton.setBounds(125, y, 200, 30);
        adaugaButton.addActionListener(e -> adaugaInternare());

        add(adaugaButton);

        y+=50;

        JButton backButton = new JButton("Inapoi");
        backButton.setBounds(125, y, 200, 30);
        backButton.addActionListener(e -> cardLayout.show(parentPanel, "InternarePanel"));
        add(backButton);

    }
    private void verificaDisponibilitate()
    {
        //salonul disponibil care are cei mai putini pacienti
        salon = services.getSalonServices().verificaDisponibilitate();
        salonField.setText(String.valueOf(salon.getIdSalon()));
    }


    public void adaugaInternare()
    {
        String diagnostic = (String) diagnosticField.getText();
        String departament = (String) departamentComboBox.getSelectedItem();

        if (!diagnostic.isEmpty() && !idPacient.getText().isEmpty() && salon!=null && departament != null){
             Internare internareNoua = services.getInternareServices().internarePacient(pacient,diagnostic,departament);
             services.getSalonServices().internarePacient(internareNoua,salon);
            JOptionPane.showMessageDialog(this, "Consultatie adăugata cu succes!");
            clearFields();

        } else {
            JOptionPane.showMessageDialog(this, "Introduceți toate datele!");
        }
    }

    public void clearFields() {
        salonField.setText("");
        diagnosticField.setText(""); // Setează câmpul de motiv la gol
        pacientInfoLabel.setText("");
        idPacient.setText("");
        departamentComboBox.setSelectedIndex(-1);

    }

    private void getDepartamente() {

        // Inițializăm un Set cu departamente predefinite
        Set<String> departamente = services.getDepartamente();

        // Adăugăm toate departamentele unice în JComboBox
        for (String departament : departamente) {
            departamentComboBox.addItem(departament);
        }
    }

}
