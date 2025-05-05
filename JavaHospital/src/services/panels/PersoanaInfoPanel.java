package services.panels;

import entities.Persoana;
import javax.swing.*;
import java.awt.*;

public abstract class PersoanaInfoPanel extends JPanel {

    protected JButton backButton;

    public PersoanaInfoPanel(Persoana persoana, String titlu)
    {
        setLayout(null);

        int y = 20;

        // Titlu
        JLabel titleLabel = new JLabel(titlu);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titleLabel.setBounds(150, y, 300, 30);
        add(titleLabel);
        y += 50;


        addLabelPair("Nume:", persoana.getNume(), y); y+=40;
        addLabelPair("Prenume:", persoana.getPrenume(), y);y+=40;
        addLabelPair("Data nasterii:", persoana.getDataNasterii().toString(), y);y+=40;
        addLabelPair("Telefon:", persoana.getTelefon(), y);y+=40;
        addLabelPair("Email:", persoana.getEmail(), y);y+=40;

        y += 20;

        // Buton back
        backButton = new JButton("Inapoi");
        backButton.setBounds(50, y, 100, 30);
        add(backButton);
    }


    private void addLabelPair(String labelText, String valueText, int y)
    {
        JLabel label = new JLabel(labelText);
        label.setBounds(50, y, 100, 30);
        add(label);

        JLabel valueLabel = new JLabel(valueText);
        valueLabel.setBounds(160, y, 300, 30);
        add(valueLabel);
    }


    protected void setBackButton(String panelName)
    {
        backButton.addActionListener(e -> {
            Container parent = getParent();
            if (parent != null && parent.getLayout() instanceof CardLayout) {
                ((CardLayout) parent.getLayout()).show(parent, panelName);
            }
        });
    }


    protected abstract void afiseazaInformatiiSpecifice();
}
