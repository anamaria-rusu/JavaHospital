package services.panels;

import services.services.Services;

import javax.swing.*;
import java.awt.*;

public class ConsultatiePanel extends JPanel {

    private CardLayout cardLayout;
    private JPanel parentPanel;
    private Services services;

    public ConsultatiePanel(CardLayout cardLayout, JPanel parentPanel, Services services)
    {
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;
        this.services = services;

        setBackground(Color.decode("#c1e6b0"));
        setLayout(null);

        int y = 30;
        addTitle("Consultații", y);
        y += 70;

        y = addButton("Vezi Consultații", "AfiseazaConsultatie", y);
        y = addButton("Adaugă Consultație", "AdaugaConsultatie", y);
        addButton("Înapoi", "Home", y);

        parentPanel.add(new ConsultatieAddPanel(services, cardLayout, parentPanel), "AdaugaConsultatie");
        parentPanel.add(new ConsultatieListPanel(services, cardLayout, parentPanel), "AfiseazaConsultatie");
    }

    private void addTitle(String title, int y)
    {
        JLabel menuLabel = new JLabel(title);
        menuLabel.setForeground(Color.WHITE);
        menuLabel.setFont(new Font("AvantGarde", Font.PLAIN, 38));
        menuLabel.setBounds(200, y, 400, 40);
        add(menuLabel);
    }

    private int addButton(String text, String targetPanel, int y)
    {
        JButton button = new JButton(text);
        button.setBounds(175, y, 250, 40);
        button.addActionListener(e -> cardLayout.show(parentPanel, targetPanel));
        add(button);
        return y + 60;
    }
}
