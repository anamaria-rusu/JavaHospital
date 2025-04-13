package services.panels;
import entities.Pacient;
import entities.Persoana;
import entities.Salon;
import services.services.Services;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class SalonPanel extends JPanel{
    Services services;
    CardLayout cardLayout;
    JPanel parentPanel;
    JList<Salon> listaSaloane;

    public SalonPanel(Services services, CardLayout cardLayout, JPanel parentPanel) {
        this.services=services;
        this.cardLayout=cardLayout;
        this.parentPanel=parentPanel;

        setBackground(Color.decode("#e6e2b0"));
        setLayout(null);

        JLabel menuLabel = new JLabel("Saloane");
        menuLabel.setForeground(Color.BLACK);
        menuLabel.setFont(new Font("AvantGarde", Font.BOLD, 38));
        menuLabel.setBounds(100, 30, 400, 40);
        add(menuLabel);


        listaSaloane = new JList<>();
        listaSaloane.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            String textAfisat = value.getIdSalon()+ " - " + " locatie: " + value.getLocatie();
            JLabel label = new JLabel(textAfisat);
            if (isSelected) {
                label.setBackground(list.getSelectionBackground());
                label.setForeground(list.getSelectionForeground());
                label.setOpaque(true);
            }
            return label;
        });

        /*
        list: lista în sine (JList)
        value: obiectul curent din listă (probabil un obiect de tip Salon)
        index: indexul elementului în listă
        isSelected: true dacă elementul este selectat
        cellHasFocus: true dacă elementul are focus
         */



        JScrollPane scrollPane = new JScrollPane(listaSaloane);
        scrollPane.setBounds(50, 100, 500, 400);
        add(scrollPane);


        listaSaloane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    Salon salon = listaSaloane.getSelectedValue();
                    if (salon != null) {
                        showSalonInfo(salon);
                    }
                }
            }
        });

        // Buton pentru actualizare
        JButton refreshButton = new JButton("Actualizează");
        refreshButton.addActionListener(e -> incarcaSaloane());
        refreshButton.setBounds(125, 570, 150, 30);
        add(refreshButton);

        JButton backButton = new JButton("Inapoi");
        backButton.addActionListener(e -> cardLayout.show(parentPanel, "InternarePanel"));
        backButton.setBounds(325, 570, 150, 30);
        add(backButton);

        incarcaSaloane();
    }

    private void incarcaSaloane() {
        List<Salon> persoane = services.getSalonServices().getSaloane();
        listaSaloane.setListData(persoane.toArray((Salon[]) new Salon[0]));
    }

    protected void showSalonInfo(Salon salon) {
        SalonInfoPanel salonInfoPanel = new SalonInfoPanel(salon,cardLayout,parentPanel,services);
        parentPanel.add(salonInfoPanel, "SalonInfoPanel");
        cardLayout.show(parentPanel, "SalonInfoPanel");
    }
}
