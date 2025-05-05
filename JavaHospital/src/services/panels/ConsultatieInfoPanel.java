package services.panels;

import entities.Consultatie;

import javax.swing.*;
import java.awt.*;

public class ConsultatieInfoPanel extends JPanel {

    private final CardLayout cardLayout;
    private final JPanel parentPanel;

    public ConsultatieInfoPanel(Consultatie consultatie, CardLayout cardLayout, JPanel parentPanel) {
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setBackground(Color.decode("#c1e6b0"));
        setLayout(null);

        int y = 20;
        addTitle("Consultație", y);
        y += 60;

        addInfoRow("Medic:", consultatie.getMedic().getNume() + " " + consultatie.getMedic().getPrenume(), y); y+=40;
        addInfoRow("Pacient:", consultatie.getPacient().getNume() + " " + consultatie.getPacient().getPrenume(), y);y+=40;
        addInfoRow("Data programării:", consultatie.getData().toString(), y);y+=40;
        addInfoRow("Ora:", consultatie.getOraProgramare().toString(), y);y+=40;
        addInfoRow("Durată estimată:", consultatie.getDurataConsultatie() + " minute", y);y+=40;
        addInfoRow("Motiv:", consultatie.getDescriere(), y);y+=40;

        addBackButton(y + 20);
    }

    private void addTitle(String text, int y)
    {
        JLabel titleLabel = new JLabel(text);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(50, y, 300, 30);
        add(titleLabel);
    }

    private void addInfoRow(String label, String value, int y)
    {
        JLabel lbl = new JLabel(label);
        lbl.setBounds(50, y, 130, 30);
        add(lbl);

        JLabel val = new JLabel(value);
        val.setBounds(180, y, 300, 30);
        add(val);

    }

    private void addBackButton(int y)
    {
        JButton backButton = new JButton("Inapoi");
        backButton.setBounds(50, y, 100, 30);
        backButton.addActionListener(e -> cardLayout.show(parentPanel, "AfiseazaConsultatie"));
        add(backButton);
    }
}
