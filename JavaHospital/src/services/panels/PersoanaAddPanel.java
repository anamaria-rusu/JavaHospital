package services.panels;

import javax.swing.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        int y = 30;
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setLayout(null);

        JLabel titleLabel = new JLabel(titlu);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titleLabel.setBounds(150, y, 200, 30);
        add(titleLabel);

        y+=50;
        JLabel numeLabel = new JLabel("Nume:");
        numeLabel.setBounds(50, y, 100, 30);
        add(numeLabel);

        numeField = new JTextField();
        numeField.setBounds(150, y, 200, 30);
        add(numeField);

        y+=40;
        JLabel prenumeLabel = new JLabel("Prenume:");
        prenumeLabel.setBounds(50, y, 100, 30);
        add(prenumeLabel);

        prenumeField = new JTextField();
        prenumeField.setBounds(150, y, 200, 30);
        add(prenumeField);

        y+=40;
        JLabel dataNasteriiLabel = new JLabel("Data nasterii:");
        dataNasteriiLabel.setBounds(50, y, 100, 30);
        add(dataNasteriiLabel);

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd-MM-yyyy");
        dateChooser.setBounds(150, y, 200, 30);
        add(dateChooser);

        y+=40;
        JLabel telefonLabel = new JLabel("Telefon:");
        telefonLabel.setBounds(50, y, 100, 30);
        add(telefonLabel);

        telefonField = new JTextField();
        telefonField.setBounds(150, y, 200, 30);
        add(telefonField);

        y+=40;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, y, 100, 30);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, y, 200, 30);
        add(emailField);

        y+=50;
        adaugaButton = new JButton("Adaugă");
        adaugaButton.setBounds(150, y, 150, 30);
        adaugaButton.addActionListener(e -> adaugaPersoana());
        add(adaugaButton);

        y+=40;
        backButton = new JButton("Înapoi");
        backButton.setBounds(150, y, 150, 30);
        add(backButton);
    }

    // formatarea datei calendaristice
    protected LocalDate getSelectedDate(JDateChooser dateChooser)
    {
        Date dataSelectata = dateChooser.getDate();
        if (dataSelectata != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String dataFormatata = dateFormat.format(dataSelectata);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return LocalDate.parse(dataFormatata, formatter);
        }
        return null;
    }

    // curatarea campurilor dupa completarea detelor
    protected void clearFields()
    {
        numeField.setText("");
        prenumeField.setText("");
        dateChooser.setDate(null);
        telefonField.setText("");
        emailField.setText("");
    }

    // configurarea corecta a butonului de back
    protected void setBackButton(String panelName)
    {
        backButton.addActionListener(e -> cardLayout.show(parentPanel, panelName));
    }

    protected abstract void adaugaPersoana();
}
