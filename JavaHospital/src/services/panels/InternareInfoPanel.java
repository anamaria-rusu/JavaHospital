package services.panels;

import entities.Internare;
import entities.Persoana;
import services.services.InternareServices;
import services.services.PacientiServices;
import services.services.Services;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


public class InternareInfoPanel extends PacientiInfoPanel {
    private Internare internare;
    JButton editButtonInternare;
    JButton saveButtonInternare;
    JButton deleteButtonInternare;



    public InternareInfoPanel(Internare internare, String backPanel, CardLayout cardLayout, JPanel parentPanel, Services services) {
        super(internare.getPacient() ,"Informații Pacient Internat",cardLayout,parentPanel,services);
        this.internare = internare;

        editButton.setVisible(false);
        saveButton.setVisible(false);
        deleteButton.setVisible(false);
        afiseazaInformatiiInternare();
        setBackButton(backPanel);
    }


    protected void afiseazaInformatiiInternare()
    {   int y= 270;
        addEditableField("Data internarii:", String.valueOf(internare.getData()),y); y+=40;
        addEditableField("Diagnostic:", internare.getDiagnostic(),y); y+=40;
        addEditableField("Departament :", internare.getDepartamentMedical(),y); y+=60;
        addEditableField("Id Salon:", String.valueOf(internare.getSalon().getId()),y); y+=40;


        editButtonInternare = new JButton("Editează");
        add(editButtonInternare);

        saveButtonInternare = new JButton("Salvează");
        saveButtonInternare.setVisible(false);
        add(saveButtonInternare);

        deleteButtonInternare = new JButton("Sterge");
        add(deleteButtonInternare);

        editButtonInternare.addActionListener(e -> {
            setEditButtonInternare();
        });

        saveButtonInternare.addActionListener(e->{
            setSaveButtonInternare(internare);
        });
        deleteButtonInternare.addActionListener(e->{
            setDeleteButtonInternare(internare.getId());
        });

        editButtonInternare.setBounds(50, y, 100, 30);
        saveButtonInternare.setBounds(50, y, 100, 30);
        backButton.setBounds(160, y, 100, 30);
        deleteButtonInternare.setBounds(270,y,100,30);
        istoricButton.setBounds(380,y,100,30);
    }

    protected void setEditButtonInternare() {
        // Creezi copii independente pentru a evita ConcurrentModificationException
        List<JLabel> lastLabels = new ArrayList<>(valueLabels.subList(valueLabels.size() - 3, valueLabels.size()));
        List<JTextField> lastTextFields = new ArrayList<>(textFields.subList(textFields.size() - 3, textFields.size()));

        valueLabels.clear();
        valueLabels.addAll(lastLabels);

        textFields.clear();
        textFields.addAll(lastTextFields);

        for (int i = 0; i < valueLabels.size(); i++) {
            JTextField tf = textFields.get(i);
            JLabel lbl = valueLabels.get(i);
            tf.setText(lbl.getText());
            lbl.setVisible(false);
            tf.setVisible(true);
        }

        editButtonInternare.setVisible(false);
        saveButtonInternare.setVisible(true);
    }

    protected void setDeleteButtonInternare(int id){
        InternareServices.getInternareServices().stergeInternare(id);
    }



    protected void setSaveButtonInternare(Internare internare)
    {
        for (int i = 0; i < valueLabels.size(); i++) {
            String newText = textFields.get(i).getText();
            valueLabels.get(i).setText(newText);
            textFields.get(i).setVisible(false);
            valueLabels.get(i).setVisible(true);
        }

        try
        {
            int id = internare.getId();
            String diagnostic  = textFields.get(0).getText();
            String departament = textFields.get(1).getText();
            int idSalon = Integer.parseInt(textFields.get(2).getText());
            InternareServices.getInternareServices().actualizeazaInternare(id,idSalon,departament,diagnostic);

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
    }

}

