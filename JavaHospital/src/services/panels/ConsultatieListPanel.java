package services.panels;

import entities.Consultatie;
import services.services.ConsultatieServices;
import services.services.Services;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ConsultatieListPanel extends JPanel
{
    private final Services services;
    private final CardLayout cardLayout;
    private final JPanel parentPanel;
    private JList<Consultatie> listaConsultatie;

    public ConsultatieListPanel(Services services, CardLayout cardLayout, JPanel parentPanel)
    {
        this.services = services;
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setBackground(Color.decode("#c1e6b0"));
        setLayout(null);

        int y = 20;
        addTitle("Consultații", y);
        y += 40;

        createConsultatieList(y);
        addButtons();

        incarcaConsultatie();
    }

    private void addTitle(String text, int y) {
        JLabel titleLabel = new JLabel(text);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(210, y, 200, 30);
        add(titleLabel);
    }

    private void createConsultatieList(int y)
    {
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
        scrollPane.setBounds(50, y, 500, 500);
        add(scrollPane);


        listaConsultatie.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    Consultatie consultatie = listaConsultatie.getSelectedValue();
                    if (consultatie != null) {
                        showConsultatieInfo(consultatie);
                    }
                }
            }
        });

    }

    private void addButtons()
    {
        JButton refreshButton = new JButton("Actualizează");
        refreshButton.setBounds(125, 570, 150, 30);
        refreshButton.addActionListener(e -> incarcaConsultatie());
        add(refreshButton);

        JButton backButton = new JButton("Înapoi");
        backButton.setBounds(325, 570, 150, 30);
        backButton.addActionListener(e -> cardLayout.show(parentPanel, "ConsultatiePanel"));
        add(backButton);
    }

    private void incarcaConsultatie()
    {
        List<Consultatie> consultatii = ConsultatieServices.getConsultatieServices().getConsultatii();
        listaConsultatie.setListData(consultatii.toArray(new Consultatie[0]));
    }

    private void showConsultatieInfo(Consultatie consultatie)
    {
        ConsultatieInfoPanel consultatieInfoPanel = new ConsultatieInfoPanel(consultatie, cardLayout, parentPanel);
        parentPanel.add(consultatieInfoPanel, "ConsultatieInfoPanel");
        cardLayout.show(parentPanel, "ConsultatieInfoPanel");
    }
}
