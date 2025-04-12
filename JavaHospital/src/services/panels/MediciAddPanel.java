package services.panels;

import services.services.MediciServices;
import java.awt.*;
import javax.swing.*;
import java.time.LocalDate;
import com.toedter.calendar.JDateChooser;
import services.services.Services;

import java.util.*;

// MediciAddPanel adauga un nou medic
// Ea extinde clasa PersoanaAddPanel care contine campuri generale pentru un individ
// Astfel, se ilustreaza reutilizarea codului

public class MediciAddPanel extends PersoanaAddPanel {
    private Services services;
    private JDateChooser dateAngajareChooser;
    private JComboBox departamentComboBox;


    public MediciAddPanel(Services services, CardLayout cardLayout, JPanel parentPanel) {
        super(cardLayout, parentPanel, "Adauga Medic");
        this.services = services;

        // Campul pentru data angajarii
        JLabel dataAngajariiLabel = new JLabel("Data angajarii:");
        dataAngajariiLabel.setBounds(50, 270, 100, 30);
        add(dataAngajariiLabel);

        dateAngajareChooser = new JDateChooser();
        dateAngajareChooser.setDateFormatString("dd-MM-yyyy");
        dateAngajareChooser.setBounds(150, 270, 200, 30);
        add(dateAngajareChooser);


        JLabel departamentLabel = new JLabel("Departament:");
        departamentLabel.setBounds(50, 310, 100, 30);
        add(departamentLabel);


        departamentComboBox = new JComboBox<>();
        departamentComboBox.setBounds(150, 310, 200, 30);
        add(departamentComboBox);

        getDepartamente();

        // Reajustam butoanele pentru a nu se suprapune
        adaugaButton.setBounds(150, 360, 150, 30);
        backButton.setBounds(150, 410, 150, 30);

        // Setam butonul de back sa ne duca inapoi in MediciPanel
        setBackButton("MediciPanel");
    }

    @Override
    protected void adaugaPersoana() {

        // extragerea informatiilor din Label-uri
        String nume = numeField.getText();
        String prenume = prenumeField.getText();
        LocalDate dataNasterii = getSelectedDate(dateChooser);  // Data nașterii
        LocalDate dataAngajarii = getSelectedDate(dateAngajareChooser);  // Data angajării
        String telefon = telefonField.getText();
        String email = emailField.getText();
        String departament = (String) departamentComboBox.getSelectedItem();

        // verificare datelor
        if (!nume.isEmpty() && !prenume.isEmpty() && dataNasterii != null && dataAngajarii != null && !telefon.isEmpty() && !email.isEmpty() && departament != null) {
            services.getMediciServices().adaugaMedic(nume, prenume, dataNasterii, email,telefon, dataAngajarii ,departament);
            JOptionPane.showMessageDialog(this, "Medic adăugat cu succes!");
            clearFields();

        } else {
            JOptionPane.showMessageDialog(this, "Introduceți toate datele!");
        }
    }

    private void getDepartamente() {

        // Inițializăm un Set cu departamente predefinite
        Set<String> departamente = services.getDepartamente();

        // Adăugăm toate departamentele unice în JComboBox
        for (String departament : departamente) {
            departamentComboBox.addItem(departament);
        }
    }


    @Override
    protected void clearFields(){
        super.clearFields();
        dateAngajareChooser.setDate(null);
        departamentComboBox.setSelectedIndex(-1);
    }

}
