package services.panels;

import entities.Consultatie;
import entities.Persoana;
import services.services.PersoanaServices;
import services.services.Services;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


public class ConsultatieListPanel extends JPanel{
    private Services services;
    private CardLayout cardLayout;
    private JPanel parentPanel;
    private JList<Consultatie> listaConsultatie;
    protected JButton backButton;

    public ConsultatieListPanel(Services services, CardLayout cardLayout, JPanel parentPanel){
        this.services=services;
        this.cardLayout=cardLayout;
        this.parentPanel=parentPanel;

        setBackground(Color.decode("#c1e6b0"));
        setLayout(null);

        // Titlu
        JLabel titleLabel = new JLabel("Consultatii");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(210, 20, 200, 30);
        add(titleLabel);

        listaConsultatie = new JList<>();
        listaConsultatie.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            String textAfisat = value.getId()+ " - " + " pacienti: " + value.getPacient().getId() + " medic: " + value.getMedic().getId();
            JLabel label = new JLabel(textAfisat);
            if (isSelected) {
                label.setBackground(list.getSelectionBackground());
                label.setForeground(list.getSelectionForeground());
                label.setOpaque(true);
            }
            return label;
        });



        JScrollPane scrollPane = new JScrollPane(listaConsultatie);
        scrollPane.setBounds(50, 60, 500, 500);
        add(scrollPane);


        // Detectăm click-urile pe listă
        listaConsultatie.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Click simplu
                    Consultatie consultatie = listaConsultatie.getSelectedValue();
                    if (consultatie != null) {
                        showConsultatieInfo(consultatie);
                    }
                }
            }
        });


        // Buton pentru actualizare
        JButton refreshButton = new JButton("Actualizează");
        refreshButton.addActionListener(e -> incarcaConsultatie());
        refreshButton.setBounds(125, 570, 150, 30);
        add(refreshButton);

        // Buton pentru întoarcere
        backButton = new JButton("Înapoi");
        backButton.addActionListener(e -> cardLayout.show(parentPanel, "ConsultatiePanel"));
        backButton.setBounds(325, 570, 150, 30);
        add(backButton);

        // Încărcăm  la inițializare
        incarcaConsultatie();
    }

    private void incarcaConsultatie() {
        List<Consultatie> persoane = services.getConsultatieService().getConsultatii();
        listaConsultatie.setListData(persoane.toArray((Consultatie[]) new Consultatie[0]));
    }


    public  void showConsultatieInfo(Consultatie consultatie) {
        ConsultatieInfoPanel consultatieInfoPanel = new ConsultatieInfoPanel(consultatie,cardLayout,parentPanel);
        parentPanel.add(consultatieInfoPanel, "ConsultatieInfoPanel");
        cardLayout.show(parentPanel, "ConsultatieInfoPanel");
    }
}




