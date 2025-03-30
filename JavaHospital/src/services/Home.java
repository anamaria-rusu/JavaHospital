package services;

import services.panels.PacientiPanel;
import services.services.PacientiServices;
import javax.swing.*;
import java.awt.*;

//clasa Home extinde clasa JFrame
//se va face o noua fereastra din care vom avea mai multe optiuni
//optiuni : - Administreaza pacienti
//          - Programari
//          -

public class Home extends JFrame
{
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private PacientiServices pacientiService;

    public Home()
    {
        //confogurari initiale
        super("JavaHospital");

        setTitle("Pagina Principala");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        pacientiService = new PacientiServices(); //serviciile disponibile pentru pacienti
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout); // panoul se va organiza folosind card-uri ( la un momennt, doar un card e vizibil in panel)

        //panoul Home
        JPanel homePanel = createHomePanel();
        homePanel.setBackground(Color.decode("#89CFF0"));
        mainPanel.add(homePanel, "Home");

        //panoul pentru administrarea pacientilor
        PacientiPanel pacientiPanel = new PacientiPanel(pacientiService, cardLayout, mainPanel);
        mainPanel.add(pacientiPanel, "PacientiPanel");

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
        JButton adminPacientiButton = new JButton("Administrează Pacienți");
        adminPacientiButton.setBounds(175, 150, 250, 40);  // Setăm poziția și dimensiunea butonului
        adminPacientiButton.addActionListener(e -> cardLayout.show(mainPanel, "PacientiPanel"));
        panel.add(adminPacientiButton);

        // Poți adăuga mai multe butoane similare
        JButton altButon = new JButton("Alt Buton");
        altButon.setBounds(175, 220, 250, 40);  // Setăm poziția și dimensiunea acestui buton
        panel.add(altButon);

        return panel;  // Returnăm panoul creat
    }

    public static void main(String[] args) {
        new Home();
    }
}







//package services;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//
//public class Home extends JFrame implements ActionListener {
//
//    private JPanel homePanel;
//    private CardLayout cardLayout; //pentru a gestiona mai multe componente ntr-un singur container/ la un moment dat doar un sigur card este vizibil
//
//    private JButton admPacentiBtn;
//    private JButton admPersonalBtn;
//
//    public Home()
//    {
//        //configurari frame Home
//        setTitle("Home Page");
//        setSize(800,600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        pacientiService = new PacientiService();
//
//        cardLayout = new CardLayout();
//        homePanel = new JPanel(cardLayout); // JPanel care foloseste "CardLayout" pentru gestionarea aspectului
//
//        //adaugarea card-urilor
//        homePanel.add(createHomePanel(), "Home");
//        homePanel.add(createAdminPanel("Administrare Pacienti"), "Pacienti");
//        homePanel.add(createAdminPanel("Administrare Personal"), "Personal");
//
//        //adaugam homePanel in frame
//        add(homePanel);
//        setVisible(true);
//
//    }
//
//
//    private JPanel createHomePanel()
//    {
//        JPanel homePanel = new JPanel();
//        homePanel.setLayout(new FlowLayout());
//        homePanel.add(createButtonPanel());
//        return homePanel;
//    }
//
//    private JPanel createButtonPanel()
//    {
//        JPanel buttonPanel = new JPanel(new FlowLayout());
//
//        admPacentiBtn = new JButton("Administreaza Pacienti");
//        admPacentiBtn.addActionListener(this);
//
//        admPersonalBtn = new JButton("Administreaza Personal");
//        admPersonalBtn.addActionListener(this);
//
//        buttonPanel.add(admPacentiBtn);
//        buttonPanel.add(admPersonalBtn);
//
//        return buttonPanel;
//
//    }
//
//    private JPanel createAdminPanel(String title)
//    {
//        JPanel panel = new JPanel(new BorderLayout());
//        JLabel label = new JLabel(title, JLabel.CENTER);
//        panel.add(label, BorderLayout.CENTER);
//        panel.add(createBackButton(), BorderLayout.SOUTH);
//        return panel;
//    }
//
//    private JButton createBackButton()
//    {
//        JButton backButton = new JButton("Inapoi");
//        backButton.addActionListener(e -> switchPanel("Home"));
//        return backButton;
//    }
//
//    private void switchPanel(String panelName) {
//        cardLayout.show(homePanel, panelName);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e)
//    {
//        if (e.getSource() == admPacentiBtn) {
//            switchPanel("Pacienti");
//        } else if (e.getSource() == admPersonalBtn) {
//            switchPanel("Medici");
//        }
//    }
//
//
//    public static void main(String[] args) {
//        new Home();
//    }
//}
