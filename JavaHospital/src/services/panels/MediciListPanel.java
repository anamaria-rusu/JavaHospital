package services.panels;

import entities.Medic;
import services.services.MediciServices;
import services.services.Services;

import javax.swing.*;
import java.awt.*;

public class MediciListPanel extends PersoanaListPanel<Medic> {

    public MediciListPanel(Services service, CardLayout cardLayout, JPanel parentPanel) {
        super(service.getMediciServices(), cardLayout, parentPanel, "Lista Medicii");
        setBackground(Color.decode("#b0e6de"));
        setBackButton("MediciPanel");

    }

    @Override
    protected void showPersoanaInfo(Medic medic) {
        MediciInfoPanel medicInfoPanel = new MediciInfoPanel(medic);
        getParentPanel().add(medicInfoPanel, "MediciInfoPanel");
        getCardLayout().show(getParentPanel(), "MediciInfoPanel");
    }
}
