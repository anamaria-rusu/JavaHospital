package services.panels;

import entities.Medic;
import entities.SugestieProgramare;
import entities.Pacient;

import services.services.PacientiServices;
import services.services.ConsultatieServices;
import services.services.DepartamenteServices;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.*;
import java.util.*;
import java.util.List;

public class ConsultatieAddPanel extends JPanel {
    private JTextField idPacient;
    private JLabel pacientInfoLabel;
    private JDateChooser dateChooser;
    private JSpinner timeSpinner;
    private JTextField durataField;
    private JTextField motivField;
    private JComboBox<String> departamentComboBox;
    private JList<SugestieProgramare> listaMedici;
    private JScrollPane scrollPane;
    private Medic medic;
    private Pacient pacient;

    public ConsultatieAddPanel(CardLayout cardLayout, JPanel parentPanel)
    {

        setBackground(Color.decode("#c1e6b0"));
        setLayout(null);

        // Titlu
        JLabel titleLabel = new JLabel("Programare Consultație");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(120, 20, 300, 30);
        add(titleLabel);

        int y = 80;

        // Selectare pacient
        JLabel pacientLabel = new JLabel("Selecteaza pacient (ID):");
        pacientLabel.setBounds(50, y, 150, 30);
        add(pacientLabel);

        idPacient = new JTextField();
        idPacient.setBounds(230, y, 100, 30);
        add(idPacient);

        JButton cautaButton = new JButton("Caută");
        cautaButton.setBounds(340, y, 100, 30);
        add(cautaButton);

        pacientInfoLabel = new JLabel("Detalii pacient: ");
        pacientInfoLabel.setBounds(50, y + 40, 400, 30);
        add(pacientInfoLabel);

        JButton detaliiPacient = new JButton("Detalii");
        detaliiPacient.setBounds(450,y,100,30);
        add(detaliiPacient);


        cautaButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idPacient.getText());
                pacient = PacientiServices.getPacientiServices().cautaEntitate(id);

                if (pacient != null)
                    pacientInfoLabel.setText("Pacient: " + pacient.getNume() + " " + pacient.getPrenume());
                else
                    pacientInfoLabel.setText("Pacientul nu a fost găsit!");
            } catch (NumberFormatException ex) {
                pacientInfoLabel.setText("ID invalid! Introdu un număr.");
            }
        });


        detaliiPacient.addActionListener(e->{
            PacientiInfoPanel pacientInfoPanel = new PacientiInfoPanel(pacient, "ConsultatieAddPanel", cardLayout, parentPanel);
            pacientInfoPanel.setBackButton("AdaugaConsultatie");
            parentPanel.add(pacientInfoPanel, "PacientiInfoPanel");
            cardLayout.show(parentPanel, "PacientiInfoPanel");

        });


        y += 80;

        // Departament
        JLabel departamentLabel = new JLabel("Departament:");
        departamentLabel.setBounds(50, y, 150, 30);
        add(departamentLabel);

        departamentComboBox = new JComboBox<>();
        departamentComboBox.setBounds(230, y, 200, 30);
        add(departamentComboBox);
        getDepartamente();

        y += 40;

        // Selectare dată
        JLabel dataLabel = new JLabel("Selectează data:");
        dataLabel.setBounds(50, y, 150, 30);
        add(dataLabel);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(230, y, 200, 30);
        dateChooser.setDateFormatString("yyyy-MM-dd");
        add(dateChooser);

        y += 40;

        // Selectare oră
        JLabel oraLabel = new JLabel("Selectează ora:");
        oraLabel.setBounds(50, y, 150, 30);
        add(oraLabel);

        SpinnerDateModel timeModel = new SpinnerDateModel();
        timeSpinner = new JSpinner(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setBounds(230, y, 100, 30);
        add(timeSpinner);

        y += 40;

        // Durata consultației
        JLabel durataLabel = new JLabel("Durata (minute):");
        durataLabel.setBounds(50, y, 150, 30);
        add(durataLabel);

        durataField = new JTextField();
        durataField.setBounds(230, y, 100, 30);
        add(durataField);

        y += 40;

        // Motiv consultație
        JLabel motivLabel = new JLabel("Motivul consultației:");
        motivLabel.setBounds(50, y, 150, 30);
        add(motivLabel);

        motivField = new JTextField();
        motivField.setBounds(230, y, 200, 30);
        add(motivField);

        y += 60;




        // Listă medici disponibili
        listaMedici = new JList<>();
        listaMedici.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            String textAfisat = "Dr. "+ value.medic().getNume() + " " + value.medic().getPrenume()+ " data: " + value.data()+ " ora: "+value.ora();
            JLabel label = new JLabel(textAfisat);
            if (isSelected) {
                label.setBackground(list.getSelectionBackground());
                label.setForeground(list.getSelectionForeground());
                label.setOpaque(true);
            }
            return label;
        });


        scrollPane = new JScrollPane(listaMedici);
        scrollPane.setBounds(50, y, 400, 100);
        add(scrollPane);

        listaMedici.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    SugestieProgramare sugestie = listaMedici.getSelectedValue();
                    if (sugestie != null) {
                        java.util.Date dataUtil = java.sql.Date.valueOf(sugestie.data());
                        dateChooser.setDate(dataUtil);
                        java.util.Date oraUtil = java.sql.Time.valueOf(sugestie.ora());
                        timeSpinner.setValue(oraUtil);
                        medic = sugestie.medic();
                    }
                }
            }
        });

        y += 120;




        // Buton verificare disponibilitate
        JButton refreshButton = new JButton("Verifică Disponibilitate");
        refreshButton.addActionListener(e -> verificaDisponibilitate());
        refreshButton.setBounds(125, y, 200, 30);
        add(refreshButton);

        y += 50;

        // Buton de adăugare consultație
        JButton adaugaButton = new JButton("Adauga Consultație");
        adaugaButton.setBounds(125, y, 200, 30);
        adaugaButton.addActionListener(e -> adaugaConsultatie());

        add(adaugaButton);

        y+=50;

        JButton backButton = new JButton("Inapoi");
        backButton.setBounds(125, y, 200, 30);
        backButton.addActionListener(e -> cardLayout.show(parentPanel, "ConsultatiePanel"));
        add(backButton);
    }


    private boolean verificaDisponibilitate()
    {
        Date dataSelectata = dateChooser.getDate();
        if (dataSelectata == null)
        {
            JOptionPane.showMessageDialog(this, "Selecteaza o data!");
            return false;
        }

        LocalDate data = new java.sql.Date(dataSelectata.getTime()).toLocalDate();
        Date oraSelectata = (Date) timeSpinner.getValue();
        LocalTime ora = new java.sql.Time(oraSelectata.getTime()).toLocalTime();

        String departament = (String) departamentComboBox.getSelectedItem();
        if (departament == null)
        {
            JOptionPane.showMessageDialog(this, "Selectează un departament!");
            return false;
        }

        int durataConsultatie;
        try
        {
            durataConsultatie = Integer.parseInt(durataField.getText());
        }
        catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(this, "Introdu o durata validă!");
            return false;
        }

        List<SugestieProgramare> disponibili = ConsultatieServices.getConsultatieServices().mediciDisponibiliConsultatie(data, ora, departament, durataConsultatie);

        if (disponibili.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Niciun medic disponibil pentru criteriile selectate!");
            return false;
        }
        else
        {
            listaMedici.setListData(disponibili.toArray(new SugestieProgramare[0]));
            return true;
        }

    }


    public void adaugaConsultatie()
    {
        String departament = (String) departamentComboBox.getSelectedItem();
        Date dataSelectata = dateChooser.getDate();
        LocalDate dataProgramare = new java.sql.Date(dataSelectata.getTime()).toLocalDate();
        Date oraSelectata = (java.util.Date) timeSpinner.getValue();
        LocalTime oraProgramare = new java.sql.Time(oraSelectata.getTime()).toLocalTime();
        Integer durataConsultatie = Integer.parseInt(durataField.getText());
        String motiv = (String) motivField.getText();

        if (!departament.isEmpty() && dataProgramare!=null && oraProgramare != null && durataConsultatie != null && !motiv.isEmpty() && verificaDisponibilitate())
        {
            ConsultatieServices.getConsultatieServices().adaugaConsultatie(medic, pacient, departament, dataProgramare,oraProgramare, durataConsultatie ,motiv);
            JOptionPane.showMessageDialog(this, "Consultatie adaugata cu succes!");
            clearFields();

        }
        else
        {
            JOptionPane.showMessageDialog(this, "Introduceti toate datele!");
        }
    }


    public void clearFields()
    {
        departamentComboBox.setSelectedIndex(0);
        dateChooser.setDate(null);
        timeSpinner.setValue(new java.util.Date());
        durataField.setText("");
        motivField.setText("");
        pacientInfoLabel.setText("");
        idPacient.setText("");
        listaMedici.setListData(new SugestieProgramare[0]);
    }


    private void getDepartamente()
    {
        Set<String> departamente = DepartamenteServices.getDepartamenteServices().getDepartamente();
        for (String departament : departamente) {
            departamentComboBox.addItem(departament);
        }
    }

}