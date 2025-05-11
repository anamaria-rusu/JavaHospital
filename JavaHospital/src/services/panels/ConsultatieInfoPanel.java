package services.panels;

import com.sun.tools.jconsole.JConsoleContext;
import entities.Consultatie;
import entities.SugestieProgramare;
import services.services.ConsultatieServices;
import services.services.PacientiServices;


import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ConsultatieInfoPanel extends JPanel {

    private final CardLayout cardLayout;
    private final JPanel parentPanel;
    protected List<JLabel> valueLabels = new ArrayList<>();
    protected List<JTextField> textFields = new ArrayList<>();


    public ConsultatieInfoPanel(Consultatie consultatie, CardLayout cardLayout, JPanel parentPanel) {
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setBackground(Color.decode("#c1e6b0"));
        setLayout(null);

        int y = 20;
        addTitle("Consultație", y);
        y += 60;

        addEditableField("Medic:", consultatie.getMedic().getNume() + " " + consultatie.getMedic().getPrenume(), y, false); y+=40;
        addEditableField("Pacient:", consultatie.getPacient().getNume() + " " + consultatie.getPacient().getPrenume(), y,false);y+=40;
        addEditableField("Data programării:", consultatie.getData().toString(), y,true);y+=40;
        addEditableField("Ora:", consultatie.getOraProgramare().toString(), y,true);y+=40;
        addEditableField("Durată estimată (minute):", String.valueOf(consultatie.getDurataConsultatie()), y,true);y+=40;
        addEditableField("Motiv:", consultatie.getDescriere(), y,true);y+=60;

        addBackButton(y);
        addEditAndSaveButton(consultatie,y);
        addDeleteButton(consultatie.getId(),y);
    }

    private void addTitle(String text, int y)
    {
        JLabel titleLabel = new JLabel(text);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(50, y, 300, 30);
        add(titleLabel);
    }


    private void addEditableField(String labelText, String valueText, int y, boolean edit)
    {
        JLabel nameLabel = new JLabel(labelText);
        nameLabel.setBounds(50, y, 100, 30);
        add(nameLabel);

        JLabel valueLabel = new JLabel(valueText);
        valueLabel.setBounds(160, y, 300, 30);
        add(valueLabel);

        if(edit)
        {
            valueLabels.add(valueLabel);
            JTextField textField = new JTextField(valueText);
            textField.setBounds(160, y, 300, 30);
            textField.setVisible(false);
            add(textField);
            textFields.add(textField);
        }

    }

    private void addBackButton(int y)
    {
        JButton backButton = new JButton("Inapoi");
        backButton.setBounds(50, y, 100, 30);
        backButton.addActionListener(e -> cardLayout.show(parentPanel, "AfiseazaConsultatie"));
        add(backButton);
    }

    private void addEditAndSaveButton(Consultatie consultatie,int y)
    {
        JButton editButton = new JButton("Editeaza");
        JButton saveButton = new JButton("Salveaza");
        editButton.setBounds(160,y,100,30);
        saveButton.setBounds(160,y,100,30);
        add(editButton);
        add(saveButton);

        editButton.addActionListener(e->{
            for (int i = 0; i < valueLabels.size(); i++) {
                JTextField tf = textFields.get(i);
                JLabel lbl = valueLabels.get(i);
                tf.setText(lbl.getText());
                lbl.setVisible(false);
                tf.setVisible(true);
            }
            editButton.setVisible(false);
            saveButton.setVisible(true);
        });


        saveButton.addActionListener(e->{
            for (int i = 0; i < valueLabels.size(); i++) {
                String newText = textFields.get(i).getText();
                valueLabels.get(i).setText(newText);
                textFields.get(i).setVisible(false);
                valueLabels.get(i).setVisible(true);
            }

            try
            {
                int id = consultatie.getId();
                String textData = textFields.get(0).getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dataProgramarii = LocalDate.parse(textData, formatter);
                textData = textFields.get(1).getText();
                formatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime oraProgramarii = LocalTime.parse(textData, formatter);
                int durataConsultatie = Integer.parseInt(textFields.get(2).getText());
                String motiv = textFields.get(3).getText();

                boolean esteDisponibil = ConsultatieServices.getConsultatieServices().esteDisponibil(id,consultatie.getMedic(),dataProgramarii,oraProgramarii,durataConsultatie);

                if(esteDisponibil)
                    ConsultatieServices.getConsultatieServices().actualizeazaConsultatie(id,dataProgramarii,oraProgramarii,durataConsultatie,motiv);

                else
                    JOptionPane.showMessageDialog(null, "Nu se poate reprograma! Medicul nu este disponibil la timpul cerut!");
            }
            catch (DateTimeParseException ex)
            {
                System.out.println("Data introdusă nu este validă");
                ex.printStackTrace();
            }
            catch(Exception ex)
            {
                System.out.println("Eroare");
                ex.printStackTrace();
            }

            editButton.setVisible(true);
            saveButton.setVisible(false);
        });

    }


    private void addDeleteButton(int id,int y){
        JButton deleteButton = new JButton("Sterge");
        deleteButton.setBounds(270,y,100,30);
        add(deleteButton);
        deleteButton.addActionListener(e->{
            ConsultatieServices.getConsultatieServices().stergeConsultatie(id);
        });
    }
}

