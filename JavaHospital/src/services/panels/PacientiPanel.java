package services.panels;

import javax.swing.*;
import java.awt.*;

public class PacientiPanel extends JPanel {
    private final CardLayout cardLayout;
    private final JPanel mainPanel;

    public PacientiPanel(CardLayout cardLayout, JPanel mainPanel)
    {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setBackground(Color.decode("#b0e1e6"));
        setLayout(null);

        initUI();
        initPanels();
    }

    private void initUI()
    {
        int y = 30;

        JLabel titleLabel = createTitleLabel("Administrare Pacienti", y);
        add(titleLabel);

        y += 70;
        addButton("Vezi Pacienti", y, "AfiseazaPacienti");

        y += 60;
        addButton("Adauga Pacient", y, "AdaugaPacienti");

        y += 60;
        addButton("Inapoi", y, "Home");
    }

    private JLabel createTitleLabel(String text, int y)
    {
        JLabel label = new JLabel(text);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("AvantGarde", Font.PLAIN, 38));
        label.setBounds(140, y, 400, 40);
        return label;
    }

    private void addButton(String text, int y, String targetPanel)
    {
        JButton button = new JButton(text);
        button.setBounds(175, y, 250, 40);
        button.addActionListener(e -> cardLayout.show(mainPanel, targetPanel));
        add(button);
    }

    private void initPanels()
    {
        mainPanel.add(new PacientiAddPanel(cardLayout, mainPanel), "AdaugaPacienti");
        mainPanel.add(new PacientiListPanel(cardLayout, mainPanel), "AfiseazaPacienti");
    }
}
