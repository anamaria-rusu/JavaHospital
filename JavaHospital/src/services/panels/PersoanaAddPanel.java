package services.panels;

import javax.swing.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Date;

// Clasa abstracta ce va fi extinsa pentru diversele tipuri de indivizi pe care ii gestionam (medici, pacienti, etc)
// Contine data generale, necesare in momentul identificarii unui individ
// Contine metode generale pentru UI (curatarea label-urilor, formatarea datei etc)

public abstract class PersoanaAddPanel extends JPanel
{
    protected JTextField numeField;
    protected JTextField prenumeField;
    protected JDateChooser dateChooser;
    protected JTextField telefonField;
    protected JTextField emailField;
    protected CardLayout cardLayout;
    protected JPanel parentPanel;
    protected JButton adaugaButton;
    protected JButton backButton;

    public PersoanaAddPanel(CardLayout cardLayout, JPanel parentPanel, String titlu)
    {
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;
        setLayout(null);

        int y = 30;

        // Titlu
        JLabel titleLabel = new JLabel(titlu);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titleLabel.setBounds(150, y, 300, 30);
        add(titleLabel);


        // Fields
        y += 50;
        numeField = addLabeledTextField("Nume:", y); y += 40;
        prenumeField = addLabeledTextField("Prenume:", y); y += 40;
        dateChooser = addDateChooser("Data nasterii:", y); y += 40;
        telefonField = addLabeledTextField("Telefon:", y); y += 40;
        emailField = addLabeledTextField("Email:", y); y += 50;

        // Buton pentru adaugarea unei persoane
        adaugaButton = new JButton("Adaugă");
        adaugaButton.setBounds(150, y, 150, 30);
        adaugaButton.addActionListener(_ ->  adaugaPersoana());
        add(adaugaButton);

        y += 40;
        // Buton pentru intoarcerea la card-ul anterior
        backButton = new JButton("Inapoi");
        backButton.setBounds(150, y, 150, 30);
        add(backButton);
    }


    // Functie pentru adaugarea unui field nou
    protected JTextField addLabeledTextField(String label, int y)
    {
        JLabel jLabel = new JLabel(label);
        jLabel.setBounds(50, y, 100, 30);
        add(jLabel);

        JTextField textField = new JTextField();
        textField.setBounds(150, y, 200, 30);
        add(textField);

        return textField;
    }


    // Functie pentru adaugarea unui DateChooser
    protected JDateChooser addDateChooser(String label, int y)
    {
        JLabel jLabel = new JLabel(label);
        jLabel.setBounds(50, y, 100, 30);
        add(jLabel);

        JDateChooser chooser = new JDateChooser();
        chooser.setDateFormatString("yyyy-MM-dd");
        chooser.setBounds(150, y, 200, 30);
        add(chooser);

        return chooser;
    }

    // Functie pentru formatarea datei calendaristice
    protected LocalDate getSelectedDate(JDateChooser dateChooser)
    {
        Date dataSelectata = dateChooser.getDate();
        if (dataSelectata != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dataFormatata = dateFormat.format(dataSelectata);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dataFormatata, formatter);
        }
        return null;
    }

    // Functie pentru verificare corectitudinii datelor introduse
    protected boolean campuriValide()
    {
        String nume = numeField.getText().trim();
        String prenume = prenumeField.getText().trim();
        Date dataNasterii = dateChooser.getDate();
        String telefon = telefonField.getText().trim();
        String email = emailField.getText().trim();

        boolean telefonValid = telefon.matches("\\d{10}");
        boolean emailValid = email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");

        return !nume.isEmpty() && !prenume.isEmpty() && dataNasterii != null && telefonValid && emailValid;
    }



    // Functie pentru curatarea campurilor dupa adaugarea persoanei
    protected void clearFields()
    {
        numeField.setText("");
        prenumeField.setText("");
        dateChooser.setDate(null);
        telefonField.setText("");
        emailField.setText("");
    }

    // Functie pentru configurarea butonului de back
    protected void setBackButton(String panelName)
    {
        backButton.addActionListener(_ -> cardLayout.show(parentPanel, panelName));
    }

    protected abstract void adaugaPersoana();
}
