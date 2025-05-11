package services.panels;

import services.services.Services;
import java.awt.*;
import javax.swing.*;
import java.time.LocalDate;

public class PacientiAddPanel extends PersoanaAddPanel
{
    private Services service;

    public PacientiAddPanel(Services service, CardLayout cardLayout, JPanel parentPanel)
    {
        super(cardLayout, parentPanel, "Adaugă Pacient");
        this.service = service;
        setBackground(Color.decode("#b0e1e6"));
        setBackButton("PacientiPanel");
    }

    // Functie pentru adaugarea unui pacient
    @Override
    protected void adaugaPersoana()
    {
        String nume = numeField.getText();
        String prenume = prenumeField.getText();
        LocalDate dataNasterii = getSelectedDate(dateChooser);
        String telefon = telefonField.getText();
        String email = emailField.getText();

        if (campuriValide())
        {
            //service.getPacientiServices().adaugaPacient(nume, prenume, dataNasterii, email, telefon);
            service.getPacientiServices().adaugaPacient(nume, prenume, dataNasterii, email, telefon);
            JOptionPane.showMessageDialog(this, "Pacient adaugat cu succes!");
            clearFields();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Completeaza corect toate informatiile!");
        }
    }
}

