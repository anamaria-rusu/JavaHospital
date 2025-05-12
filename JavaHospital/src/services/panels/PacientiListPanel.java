package services.panels;

import entities.Pacient;
import services.services.PacientiServices;

import javax.swing.*;
import java.awt.*;

public class PacientiListPanel extends PersoanaListPanel<Pacient>
{

    public PacientiListPanel(CardLayout cardLayout, JPanel parentPanel)
    {

        super(PacientiServices.getPacientiServices(), cardLayout, parentPanel, "Lista Pacienți");

        setBackground(Color.decode("#b0e1e6"));
        setBackButton("PacientiPanel");
    }

    @Override
    protected void showPersoanaInfo(Pacient pacient)
    {
        PacientiInfoPanel pacientInfoPanel = new PacientiInfoPanel(pacient, "AfiseazaPacienti",getCardLayout(), getParentPanel());
        getParentPanel().add(pacientInfoPanel, "PacientiInfoPanel");
        getCardLayout().show(getParentPanel(), "PacientiInfoPanel");
    }
}
