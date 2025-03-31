package services.panels;

import services.services.PacientiServices;
import java.awt.*;
import javax.swing.*;
import java.time.LocalDate;

// PacientiAddPanel adauga un nou pacient
// Ea extinde clasa PersoanaAddPanel care contine campuri generale pentru un individ
// Astfel, se ilustreaza reutilizarea codului

public class PacientiAddPanel extends PersoanaAddPanel {
    private PacientiServices service;

    public PacientiAddPanel(PacientiServices service, CardLayout cardLayout, JPanel parentPanel)
    {
        super(cardLayout, parentPanel, "Adaugă Pacient");
        this.service = service;

        // Setam butonul de back sa ne duca inapoi in MediciPanel
        setBackButton("PacientiPanel");
    }

    @Override
    protected void adaugaPersoana()
    {
        // extragerea informatiilor din Label-uri
        String nume = numeField.getText();
        String prenume = prenumeField.getText();
        LocalDate dataNasterii = getSelectedDate(dateChooser);
        String telefon = telefonField.getText();
        String email = emailField.getText();

        // verificare datelor
        if (!nume.isEmpty() && !prenume.isEmpty() && dataNasterii != null && !telefon.isEmpty() && !email.isEmpty()) {
            service.adaugaPacient(nume, prenume, dataNasterii, telefon, email);
            JOptionPane.showMessageDialog(this, "Pacient adăugat cu succes!");
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Introduceți toate datele!");
        }
    }

}
