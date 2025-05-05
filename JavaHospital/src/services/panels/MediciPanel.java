package services.panels;

import services.services.Services;
import javax.swing.*;
import java.awt.*;

public class MediciPanel extends JPanel
{
    private final CardLayout cardLayout;
    private final JPanel parentPanel;
    private final Services services;

    public MediciPanel(Services services, CardLayout cardLayout, JPanel parentPanel)
    {
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;
        this.services = services;

        setBackground(Color.decode("#b0e6de"));
        setLayout(null);

        initUI();
        initPanels();
    }

    private void initUI() {
        int y = 30;

        JLabel titleLabel = createTitleLabel("Administrare Medici", y);
        add(titleLabel);

        y += 70;
        addButton("Vezi Medici", y, "AfiseazaMedici");

        y += 60;
        addButton("Adaugă Medic", y, "AdaugaMedici");

        y += 60;
        addButton("Înapoi", y, "AngajatiPanel");
    }

    private JLabel createTitleLabel(String text, int y)
    {
        JLabel label = new JLabel(text);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("AvantGarde", Font.PLAIN, 38));
        label.setBounds(100, y, 400, 40);
        return label;
    }

    private void addButton(String text, int y, String targetPanel)
    {
        JButton button = new JButton(text);
        button.setBounds(175, y, 250, 40);
        button.addActionListener(e -> cardLayout.show(parentPanel, targetPanel));
        add(button);
    }

    private void initPanels()
    {
        parentPanel.add(new MediciAddPanel(services, cardLayout, parentPanel), "AdaugaMedici");
        parentPanel.add(new MediciListPanel(services, cardLayout, parentPanel), "AfiseazaMedici");
    }
}
