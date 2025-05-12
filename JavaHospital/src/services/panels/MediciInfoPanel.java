package services.panels;

import entities.Medic;
import entities.Persoana;
import services.services.*;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MediciInfoPanel extends PersoanaInfoPanel {

    private final Medic medic;

    public MediciInfoPanel(Medic medic) {

        super(medic, "Informaii Medic");
        this.medic = medic;

        setBackground(Color.decode("#b0e6de"));

        afiseazaInformatiiSpecifice();
        setBackButton("AfiseazaMedici");
    }

    @Override
    protected void afiseazaInformatiiSpecifice()
    {
        int y = 270;

        addEditableField("Data angajării:", medic.getDataAngajarii().toString(), y); y += 40;
        addEditableField("Departament:", medic.getDepartamentMedical(), y); y += 60;


        editButton.setBounds(50, y, 100, 30);
        saveButton.setBounds(50, y, 100, 30);
        backButton.setBounds(160, y, 100, 30);
        deleteButton.setBounds(270,y,100,30);

    }

    @Override
    protected void setDeleteButton(int id){
        MediciServices.getMediciServices().stergeMedic(id);
    }

    @Override
    protected void setSaveButton(Persoana persoana)
    {
        for (int i = 0; i < valueLabels.size(); i++) {
            String newText = textFields.get(i).getText();
            valueLabels.get(i).setText(newText);
            textFields.get(i).setVisible(false);
            valueLabels.get(i).setVisible(true);
        }

        try
        {
            int id = persoana.getId();

            String nume  = textFields.get(0).getText();
            String prenume = textFields.get(1).getText();
            String textData = textFields.get(2).getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dataNasterii = LocalDate.parse(textData, formatter);
            String telefon = textFields.get(3).getText();
            String email = textFields.get(4).getText();

            textData = textFields.get(5).getText();
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dataAngajarii = LocalDate.parse(textData, formatter);
            String departamentMedical = textFields.get(4).getText();


            MediciServices.getMediciServices().actualizeazaMedic(id,nume,prenume,dataNasterii,telefon,email,dataAngajarii,departamentMedical);

        }
        catch (DateTimeParseException ex)
        {

            ex.printStackTrace();
        }
        catch(Exception ex)
        {

            ex.printStackTrace();
        }

        editButton.setVisible(true);
        saveButton.setVisible(false);
    }


}
