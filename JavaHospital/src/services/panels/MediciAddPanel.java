package services.panels;

import services.services.MediciServices;
import java.awt.*;
import javax.swing.*;
import java.time.LocalDate;
import com.toedter.calendar.JDateChooser;

// MediciAddPanel adauga un nou medic
// Ea extinde clasa PersoanaAddPanel care contine campuri generale pentru un individ
// Astfel, se ilustreaza reutilizarea codului

public class MediciAddPanel extends PersoanaAddPanel {
    private MediciServices service;
    private JDateChooser dateAngajareChooser;

    public MediciAddPanel(MediciServices service, CardLayout cardLayout, JPanel parentPanel) {
        super(cardLayout, parentPanel, "Adauga Medic");
        this.service = service;

        // Campul pentru data angajarii
        JLabel dataAngajariiLabel = new JLabel("Data angajarii:");
        dataAngajariiLabel.setBounds(50, 270, 100, 30);
        add(dataAngajariiLabel);

        dateAngajareChooser = new JDateChooser();
        dateAngajareChooser.setDateFormatString("dd-MM-yyyy");
        dateAngajareChooser.setBounds(150, 270, 200, 30);
        add(dateAngajareChooser);

        // Reajustam butoanele pentru a nu se suprapune
        adaugaButton.setBounds(150, 320, 150, 30);
        backButton.setBounds(150, 360, 150, 30);

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

        // verificare datelor
        if (!nume.isEmpty() && !prenume.isEmpty() && dataNasterii != null && dataAngajarii != null && !telefon.isEmpty() && !email.isEmpty()) {
            service.adaugaMedic(nume, prenume, dataNasterii, email,telefon, dataAngajarii ,"");
            JOptionPane.showMessageDialog(this, "Medic adăugat cu succes!");
            clearFields();

        } else {
            JOptionPane.showMessageDialog(this, "Introduceți toate datele!");
        }
    }

//    private LocalDate getSelectedDateFromChooser(JDateChooser chooser) {
//        java.util.Date dataSelectata = chooser.getDate();
//        if (dataSelectata != null) {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//            String dataFormatata = dateFormat.format(dataSelectata);
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//            return LocalDate.parse(dataFormatata, formatter);
//        }
//        return null;
//    }

    @Override
    protected void clearFields(){
        super.clearFields();
        dateAngajareChooser.setDate(null);
    }

}
