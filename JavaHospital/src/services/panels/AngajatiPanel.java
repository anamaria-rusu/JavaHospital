package services.panels;

import javax.swing.*;
import java.awt.*;

public class AngajatiPanel extends JPanel
{
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public AngajatiPanel(CardLayout cardLayout, JPanel mainPanel)
    {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setBackground(Color.decode("#b0e6de"));
        setLayout(null);

        int y = 30;

        JLabel menuLabel = new JLabel("Administrare Angajati");
        menuLabel.setFont(new Font("AvantGarde", Font.PLAIN, 38));
        menuLabel.setBounds(100, y, 400, 40); y+=80;
        add(menuLabel);

        adaugareButon("Medici", y, "MediciPanel");y+=60;
        adaugareButon("Asistente", y, "AsistentePanel");y+=60;
        adaugareButon("Inapoi", y, "Home");

        mainPanel.add(new MediciPanel(cardLayout, mainPanel), "MediciPanel");
    }

    private void adaugareButon(String text, int y, String cardName)
    {
        JButton button = new JButton(text);
        button.setBounds(175, y, 250, 40);
        button.addActionListener(e -> cardLayout.show(mainPanel, cardName));
        add(button);
    }
}
