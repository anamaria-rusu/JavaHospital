package services.panels;

import entities.Pacient;
import services.services.PacientiServices;
import services.services.Services;

import javax.swing.*;
import java.awt.*;

public class PacientiListPanel extends PersoanaListPanel<Pacient> {

    public PacientiListPanel(Services service, CardLayout cardLayout, JPanel parentPanel) {
        super(service.getPacientiServices(), cardLayout, parentPanel, "Lista Pacienți");
        setBackButton("PacientiPanel");
    }

    @Override
    protected void showPersoanaInfo(Pacient pacient) {
        PacientiInfoPanel pacientInfoPanel = new PacientiInfoPanel(pacient, "AfiseazaPacienti");
        getParentPanel().add(pacientInfoPanel, "PacientiInfoPanel");
        getCardLayout().show(getParentPanel(), "PacientiInfoPanel");
    }
}
