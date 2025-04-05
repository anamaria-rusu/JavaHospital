package services.panels;

import entities.Pacient;
import javax.swing.*;
import java.awt.*;

public class PacientiInfoPanel extends PersoanaInfoPanel {
    private Pacient pacient;

    public PacientiInfoPanel(Pacient pacient, String backPanel) {
        super(pacient, "Informații Pacient");
        this.pacient = pacient;
        setBackButton(backPanel);
    }

    @Override
    protected void afiseazaInformatiiSpecifice() {}
}
