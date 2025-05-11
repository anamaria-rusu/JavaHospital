package services;

import services.panels.InternarePanel;
import services.panels.PacientiPanel;
import services.panels.AngajatiPanel;
import services.panels.ConsultatiePanel;
import services.services.Services;

import javax.swing.*;
import java.awt.*;


// clasa Home extinde clasa JFrame
// se va face o noua fereastra din care vom avea mai multe optiuni
// optiuni : - Administreaza pacienti
//           - Administreaza Angajati
//           - Consultatii
//           - Internari

public class Home extends JFrame
{
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Services services;

    public Home()
    {
        //configurari initiale
        super("JavaHospital");

        setTitle("Pagina Principala");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // panoul se va organiza folosind card-uri ( la un momennt, doar un card e vizibil in panel)
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        services = new Services();

        //panoul Home
        JPanel homePanel = createHomePanel();
        homePanel.setBackground(Color.decode("#b0c7e6"));
        mainPanel.add(homePanel, "Home");

        //panoul pentru administrarea pacientilor
        PacientiPanel pacientiPanel = new PacientiPanel(services, cardLayout, mainPanel);
        mainPanel.add(pacientiPanel, "PacientiPanel");

        //panoul pentru administrarea angajatilor
        AngajatiPanel angajatiPanel = new AngajatiPanel(services, cardLayout,mainPanel);
        mainPanel.add(angajatiPanel, "AngajatiPanel");

        // panoul pentru consulatii
        ConsultatiePanel consultatiePanel = new ConsultatiePanel(cardLayout,mainPanel,services);
        mainPanel.add(consultatiePanel, "ConsultatiePanel");

        // panoul pentru internari
        InternarePanel internarePanel = new InternarePanel(services, cardLayout, mainPanel);
        mainPanel.add(internarePanel, "InternarePanel");


        add(mainPanel);
        setVisible(true);
    }

    private JPanel createHomePanel()
    {
        // Cream panoul Home care va avea butoane pentru functionalitatile disponibile
        JPanel panel = new JPanel();
        // Setam layout-ul la null pentru a folosi setBounds
        panel.setLayout(null);


        // variabila y care ne indica pozitia de dreapta oY in sistemul cartezian
        int y = 50;
        // Eticheta "Meniu"
        JLabel menuLabel = new JLabel("Meniu");
        menuLabel.setForeground(Color.BLACK);
        menuLabel.setFont(new Font("AvantGarde", Font.PLAIN,48));
        menuLabel.setBounds(230, y, 200, 40);
        panel.add(menuLabel);

        y+=70;
        // Buton pentru navigarea către panoul de Administrare al pacienților
        JButton adminPacientiButton = new JButton("Administreaza Pacienti");
        adminPacientiButton.setBounds(175, y, 250, 40);
        adminPacientiButton.addActionListener(e -> cardLayout.show(mainPanel, "PacientiPanel"));
        panel.add(adminPacientiButton);

        y+=70;
        // Buton pentru navigarea către panoul de Administrare al angajatilor
        JButton adminAngajatiButton = new JButton("Administreaza Angajati");
        adminAngajatiButton.setBounds(175, y, 250, 40);
        adminAngajatiButton.addActionListener(e-> cardLayout.show(mainPanel,"AngajatiPanel"));
        panel.add(adminAngajatiButton);

        y+=70;
        //  Buton pentru navigarea către panoul de consultatii
        JButton consulatieButton = new JButton("Consultatii");
        consulatieButton.setBounds(175, y, 250, 40);
        consulatieButton.addActionListener(e-> cardLayout.show(mainPanel,"ConsultatiePanel"));
        panel.add(consulatieButton);

        y+=70;
        //  Buton pentru navigarea către panoul de internari
        JButton internareButton = new JButton("Internari");
        internareButton.setBounds(175, y, 250, 40);
        internareButton.addActionListener(e-> cardLayout.show(mainPanel,"InternarePanel"));
        panel.add(internareButton);

        return panel;
    }

    public static void main(String[] args) {
        new Home();
    }
}



