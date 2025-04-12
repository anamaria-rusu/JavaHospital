package services;

import services.panels.InternarePanel;
import services.panels.PacientiPanel;
import services.panels.AngajatiPanel;
import services.panels.ConsultatiePanel;
import services.services.Services;

import javax.swing.*;
import java.awt.*;
import java.lang.management.ManagementFactory;

//clasa Home extinde clasa JFrame
//se va face o noua fereastra din care vom avea mai multe optiuni
//optiuni : - Administreaza pacienti
//          - Programari
//          -

public class Home extends JFrame
{
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Services services;

    public Home()
    {
        //confogurari initiale
        super("JavaHospital");

        setTitle("Pagina Principala");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout); // panoul se va organiza folosind card-uri ( la un momennt, doar un card e vizibil in panel)
        services = new Services();

        //panoul Home
        JPanel homePanel = createHomePanel();
        homePanel.setBackground(Color.decode("#89CFF0"));
        mainPanel.add(homePanel, "Home");

        //panoul pentru administrarea pacientilor
        PacientiPanel pacientiPanel = new PacientiPanel(services, cardLayout, mainPanel);
        mainPanel.add(pacientiPanel, "PacientiPanel");

        //panoul pentru administrarea angajatilor din sistem
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
        // Creăm panoul Home care va avea butoane pentru funcționalitățile disponibile în aplicație
        JPanel panel = new JPanel();
        panel.setLayout(null);  // Setăm layout-ul la null pentru a folosi setBounds

        // Creăm eticheta "Meniu"
        JLabel menuLabel = new JLabel("Meniu");
        menuLabel.setForeground(Color.WHITE);
        menuLabel.setFont(new Font("AvantGarde", Font.BOLD, 38));
        menuLabel.setBounds(230, 50, 200, 40);  // Setăm poziția și dimensiunea etichetei
        panel.add(menuLabel);

        // Buton pentru navigarea către panoul de Administrare al pacienților
        JButton adminPacientiButton = new JButton("Administreaza Pacienti");
        adminPacientiButton.setBounds(175, 150, 250, 40);  // Setăm poziția și dimensiunea butonului
        adminPacientiButton.addActionListener(e -> cardLayout.show(mainPanel, "PacientiPanel"));
        panel.add(adminPacientiButton);

        // Buton pentru navigarea către panoul de Administrare al angajatilor
        JButton adminAngajatiButton = new JButton("Administreaza Angajati");
        adminAngajatiButton.setBounds(175, 220, 250, 40);  // Setăm poziția și dimensiunea acestui buton
        adminAngajatiButton.addActionListener(e-> cardLayout.show(mainPanel,"AngajatiPanel"));
        panel.add(adminAngajatiButton);


        //  Buton pentru navigarea către panoul de consultatii
        JButton consulatieButton = new JButton("Consultatii");
        consulatieButton.setBounds(175, 290, 250, 40);  // Setăm poziția și dimensiunea acestui buton
        consulatieButton.addActionListener(e-> cardLayout.show(mainPanel,"ConsultatiePanel"));
        panel.add(consulatieButton);


        //  Buton pentru navigarea către panoul de internari
        JButton internareButton = new JButton("Internari");
        internareButton.setBounds(175, 360, 250, 40);  // Setăm poziția și dimensiunea acestui buton
        internareButton.addActionListener(e-> cardLayout.show(mainPanel,"InternarePanel"));
        panel.add(internareButton);

        return panel;  // Returnăm panoul creat
    }

    public static void main(String[] args) {
        new Home();
    }
}




