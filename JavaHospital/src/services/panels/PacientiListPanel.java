package services.panels;

import entities.Pacient;
import services.services.Services;

import javax.swing.*;
import java.awt.*;

public class PacientiListPanel extends PersoanaListPanel<Pacient>
{

    private Services services;
    public PacientiListPanel(Services service, CardLayout cardLayout, JPanel parentPanel)
    {

        super(service.getPacientiServices(), cardLayout, parentPanel, "Lista Pacienți");
        this.services = service;
        setBackground(Color.decode("#b0e1e6"));
        setBackButton("PacientiPanel");
    }

    @Override
    protected void showPersoanaInfo(Pacient pacient)
    {
        PacientiInfoPanel pacientInfoPanel = new PacientiInfoPanel(pacient, "AfiseazaPacienti",getCardLayout(), getParentPanel(), services);
        getParentPanel().add(pacientInfoPanel, "PacientiInfoPanel");
        getCardLayout().show(getParentPanel(), "PacientiInfoPanel");
    }
}
