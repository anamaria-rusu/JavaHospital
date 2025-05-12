package services.panels;

import com.toedter.calendar.JDateChooser;
import entities.Persoana;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import services.services.PacientiServices;

public abstract class PersoanaInfoPanel extends JPanel {

    protected JButton backButton;
    protected JButton editButton;
    protected JButton saveButton;
    protected JButton deleteButton;
    protected List<JLabel> valueLabels = new ArrayList<>();
    protected List<JTextField> textFields = new ArrayList<>();

    public PersoanaInfoPanel(Persoana persoana, String titlu) {
        setLayout(null);

        int y = 20;

        // Titlu
        JLabel titleLabel = new JLabel(titlu);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titleLabel.setBounds(150, y, 300, 30);
        add(titleLabel);
        y += 50;

        // Folosim addEditableField în loc de addLabelPair
        addEditableField("Nume:", persoana.getNume(), y); y += 40;
        addEditableField("Prenume:", persoana.getPrenume(), y); y += 40;
        addEditableField("Data nasterii:", persoana.getDataNasterii().toString(), y); y += 40;
        addEditableField("Telefon:", persoana.getTelefon(), y); y += 40;
        addEditableField("Email:", persoana.getEmail(), y); y += 60;


        editButton = new JButton("Editează");
        editButton.setBounds(50, y, 100, 30);
        add(editButton);

        saveButton = new JButton("Salvează");
        saveButton.setBounds(50, y, 100, 30);
        saveButton.setVisible(false);
        add(saveButton);

        backButton = new JButton("Înapoi");
        backButton.setBounds(160, y, 100, 30);
        add(backButton);

        deleteButton = new JButton("Sterge");
        deleteButton.setBounds(270,y,100,30);
        add(deleteButton);

        // Acțiune pentru editare
        editButton.addActionListener(e -> {
            setEditButton();
        });

        saveButton.addActionListener(e -> {
           setSaveButton(persoana);
        });

        deleteButton.addActionListener(e -> {
            setDeleteButton(persoana.getId());
        });
    }

    protected void addEditableField(String labelText, String valueText, int y)
    {
        JLabel nameLabel = new JLabel(labelText);
        nameLabel.setBounds(50, y, 100, 30);
        add(nameLabel);

        JLabel valueLabel = new JLabel(valueText);
        valueLabel.setBounds(160, y, 300, 30);
        add(valueLabel);
        valueLabels.add(valueLabel);

        JTextField textField = new JTextField(valueText);
        textField.setBounds(160, y, 300, 30);
        textField.setVisible(false);
        add(textField);
        textFields.add(textField);
    }


    protected void setBackButton(String panelName) {
        backButton.addActionListener(e -> {
            Container parent = getParent();
            if (parent != null && parent.getLayout() instanceof CardLayout) {
                ((CardLayout) parent.getLayout()).show(parent, panelName);
            }
        });
    }

    protected void setEditButton(){
        for (int i = 0; i < valueLabels.size(); i++) {
            JTextField tf = textFields.get(i);
            JLabel lbl = valueLabels.get(i);
            tf.setText(lbl.getText());
            lbl.setVisible(false);
            tf.setVisible(true);
        }
        editButton.setVisible(false);
        saveButton.setVisible(true);
    }

    protected abstract void setSaveButton(Persoana persoana);
    protected abstract void setDeleteButton(int id);
    protected abstract void afiseazaInformatiiSpecifice();
}
