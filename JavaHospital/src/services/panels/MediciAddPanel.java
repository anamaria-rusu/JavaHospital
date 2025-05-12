package services.panels;

import services.services.DepartamenteServices;
import services.services.MediciServices;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import com.toedter.calendar.JDateChooser;
import java.util.Set;

public class MediciAddPanel extends PersoanaAddPanel
{
    private JDateChooser dateAngajareChooser;
    private JComboBox<String> departamentComboBox;

    public MediciAddPanel(CardLayout cardLayout, JPanel parentPanel)
    {
        super(cardLayout, parentPanel, "Adaugă Medic");

        setBackground(Color.decode("#b0e6de"));

        initMediciFields();
        setBackButton("MediciPanel");
    }


    // functie pentru adaugarea noilor campuri specifice unui medic
    private void initMediciFields()
    {
        int y = 280;

        // Data angajarii
        dateAngajareChooser = super.addDateChooser("Data angajarii:", y);   y += 40;

        // Departament
        departamentComboBox = addLabeledComboBox("Departament:", y);
        populateDepartamente();

        // Repozitionare butoane
        y += 50;
        adaugaButton.setBounds(150, y, 150, 30);
        y += 50;
        backButton.setBounds(150, y, 150, 30);

    }

    // Functie pentru adaugarea unui ComboBox
    protected JComboBox<String> addLabeledComboBox(String labelText, int y)
    {
        JLabel label = new JLabel(labelText);
        label.setBounds(50, y, 100, 30);
        add(label);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(150, y, 200, 30);
        add(comboBox);

        return comboBox;
    }


    // Functie pentru populara ComoBox-ului cu departamentele spitalului
    private void populateDepartamente()
    {
        Set<String> departamente = DepartamenteServices.getDepartamenteServices().getDepartamente();
        if (departamente != null && !departamente.isEmpty()) {
            for (String dep : departamente) {
                departamentComboBox.addItem(dep);
            }
        } else {
            departamentComboBox.addItem("Nedefinit");
        }
        departamentComboBox.setSelectedIndex(-1);
    }


    // Functie pentru adaugarea unui medic
    @Override
    protected void adaugaPersoana()
    {
        String nume = numeField.getText();
        String prenume = prenumeField.getText();
        LocalDate dataNasterii = getSelectedDate(dateChooser);
        LocalDate dataAngajarii = getSelectedDate(dateAngajareChooser);
        String telefon = telefonField.getText();
        String email = emailField.getText();
        String departament = (String) departamentComboBox.getSelectedItem();

        if (campuriValide() && dataAngajarii != null && departament != null)
        {
            MediciServices.getMediciServices().adaugaMedic(nume, prenume, dataNasterii, email, telefon, dataAngajarii, departament);
            JOptionPane.showMessageDialog(this, "Medic adaugat cu succes!");
            clearFields();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Completeaza corect toate informatiile!");
        }
    }


    @Override
    protected void clearFields()
    {
        super.clearFields();
        dateAngajareChooser.setDate(null);
        departamentComboBox.setSelectedIndex(-1);
    }
}
