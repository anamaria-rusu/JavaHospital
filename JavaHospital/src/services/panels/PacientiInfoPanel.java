package services.panels;

import entities.Pacient;
import entities.Persoana;
import services.services.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PacientiInfoPanel extends PersoanaInfoPanel {
    private Pacient pacient;
    protected JButton istoricButton;
    private CardLayout cardLayout;
    private JPanel parentPanel;
    private Services services;

    public PacientiInfoPanel(Pacient pacient, String backPanel, CardLayout cardLayout, JPanel parentPanel, Services service) {
        super(pacient, "Informații Pacient");
        this.pacient = pacient;
        this.cardLayout =cardLayout;
        this.parentPanel = parentPanel;
        this.services=service;
        setBackground(Color.decode("#b0e1e6"));
        afiseazaInformatiiSpecifice();
        setBackButton(backPanel);
    }

    @Override
    protected void afiseazaInformatiiSpecifice()
    {
        istoricButton = new JButton("Istoric Medical");
        istoricButton.setBounds(50, 330, 200, 30);
        istoricButton.addActionListener(e->cardLayout.show(parentPanel, "IstoricPacient") );
        add(istoricButton);
        parentPanel.add(new IstoricPacientPanel(pacient,cardLayout,parentPanel,services), "IstoricPacient");

    }

    @Override
    protected void setDeleteButton(int id){
        PacientiServices.getPacientiServices().stergePacient(id);
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
            PacientiServices.getPacientiServices().actualizeazaPacient(id,nume,prenume,dataNasterii,telefon,email);

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
