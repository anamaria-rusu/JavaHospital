package services.panels;

import entities.Medic;
import services.services.MediciServices;
import javax.swing.*;
import java.awt.*;

public class MediciListPanel extends PersoanaListPanel<Medic> {

    public MediciListPanel(MediciServices service, CardLayout cardLayout, JPanel parentPanel) {
        super(service, cardLayout, parentPanel, "Lista Medicii");
        setBackButton("MediciPanel");
    }

    @Override
    protected void showPersoanaInfo(Medic medic) {
        MediciInfoPanel medicInfoPanel = new MediciInfoPanel(medic);
        getParentPanel().add(medicInfoPanel, "MediciInfoPanel");
        getCardLayout().show(getParentPanel(), "MediciInfoPanel");
    }
}
