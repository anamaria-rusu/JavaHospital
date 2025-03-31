package services.panels;

import entities.Pacient;
import services.services.PacientiServices;
import javax.swing.*;
import java.awt.*;

public class PacientiListPanel extends PersoanaListPanel<Pacient> {

    public PacientiListPanel(PacientiServices service, CardLayout cardLayout, JPanel parentPanel) {
        super(service, cardLayout, parentPanel, "Lista Pacienți");
        setBackButton("PacientiPanel");
    }

    @Override
    protected void showPersoanaInfo(Pacient pacient) {
        PacientiInfoPanel pacientInfoPanel = new PacientiInfoPanel(pacient);
        getParentPanel().add(pacientInfoPanel, "PacientiInfoPanel");
        getCardLayout().show(getParentPanel(), "PacientiInfoPanel");
    }
}
