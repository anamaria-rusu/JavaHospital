package services.panels;

import services.services.Services;

import javax.swing.*;
import java.awt.*;

// panel ce va contine meniul pentru angajatii spitalului
// angajatii sunt medici si asistene


public class AngajatiPanel extends JPanel {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Services services;

    public AngajatiPanel(Services services,CardLayout cardLayout, JPanel mainPanel){
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        this.services=services;

        //configurarea UI

        setBackground(Color.decode("#89CFF0"));
        setLayout(null);

        JLabel menuLabel = new JLabel("Administrare Angajati");
        menuLabel.setFont(new Font("AvantGarde", Font.BOLD, 38));
        menuLabel.setBounds(100, 30, 400, 40);
        add(menuLabel);

        JButton mediciButton = new JButton("Medici");
        mediciButton.setBounds(175, 100, 250, 40);
        mediciButton.addActionListener(e->cardLayout.show(mainPanel, "MediciPanel"));
        add(mediciButton);

        JButton asistenteButton = new JButton("Asistente");
        asistenteButton.setBounds(175, 160, 250, 40);
        asistenteButton.addActionListener(e->cardLayout.show(mainPanel, "AsistentePanel"));
        add(asistenteButton);

        JButton homePanelButton = new JButton("Inapoi");
        homePanelButton.setBounds(175,220,250,40);
        homePanelButton.addActionListener(e->cardLayout.show(mainPanel,"Home"));
        add(homePanelButton);


        //Legaturi cu MediciPanel si AsistentePanel
        mainPanel.add(new MediciPanel(services,cardLayout,mainPanel),"MediciPanel");
//      mainPanel.add(new AsistentePanel(cardLayout,mainPanel),"MediciPanel");
    }

}
