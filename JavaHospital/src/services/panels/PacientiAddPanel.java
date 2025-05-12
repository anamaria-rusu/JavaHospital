package services.panels;

import services.services.*;
import java.awt.*;
import javax.swing.*;
import java.time.LocalDate;

public class PacientiAddPanel extends PersoanaAddPanel
{

    public PacientiAddPanel(CardLayout cardLayout, JPanel parentPanel)
    {
        super(cardLayout, parentPanel, "Adaugă Pacient");
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
            PacientiServices.getPacientiServices().adaugaPacient(nume, prenume, dataNasterii, email, telefon);
            JOptionPane.showMessageDialog(this, "Pacient adaugat cu succes!");
            clearFields();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Completeaza corect toate informatiile!");
        }
    }
}

