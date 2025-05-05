package services.services;

import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;
import entities.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;

public class IstoricMedicalService {

    public int getNrServicii(IstoricPacient istoricPacient ){
        return istoricPacient.getServiciiMedicale().size();
    }

    public String timpMediuIntreConsultatii(IstoricPacient istoricPacient){
        List<ServiciuMedical> serviciiMedicale = istoricPacient.getServiciiMedicale();
        List<Consultatie> consultatii = new ArrayList<>();
        long timpTotal = 0;
        long mediaZile = 0;
        LocalDate data1;
        LocalDate data2;

        for (ServiciuMedical serviciuMedical : serviciiMedicale) {
            if (serviciuMedical instanceof Consultatie consultatie) {
                consultatii.add(consultatie);
            }
        }

        consultatii.sort(Comparator.comparing(Consultatie::getData));
        for(int i=0; i< consultatii.size()-1;i++){
            data1 = consultatii.get(i).getData();
            data2 = consultatii.get(i+1).getData();
            timpTotal += ChronoUnit.DAYS.between(data1, data2);

        }

        if (consultatii.size()==1)
            return "Prea putine date pentru calculul statisticilor";

        mediaZile = timpTotal / (consultatii.size() - 1);

        LocalDate azi = LocalDate.now();
        LocalDate dupaMedia = azi.plusDays(mediaZile);
        Period perioada = Period.between(azi, dupaMedia);

        return formateazaPerioada(perioada);
    }

    private String formateazaPerioada(Period p) {
        StringBuilder sb = new StringBuilder();
        if (p.getYears() > 0) sb.append(p.getYears()).append(" ani ");
        if (p.getMonths() > 0) sb.append(p.getMonths()).append(" luni ");
        if (p.getDays() > 0) sb.append(p.getDays()).append(" zile ");
        return sb.toString().trim();
    }

    public String celeMaiFrecventeAfectiuni(IstoricPacient istoricPacient) {
        Map<String, Integer> frecventa = new HashMap<>();
        List<ServiciuMedical> serviciiMedicale = istoricPacient.getServiciiMedicale();

        for (ServiciuMedical serviciu : serviciiMedicale) {
            String departament = serviciu.getDepartamentMedical();
            if (frecventa.containsKey(departament)) {
                frecventa.put(departament, frecventa.get(departament) + 1);
            } else {
                frecventa.put(departament, 1);
            }
        }


        int max = 0;
        for (String departament : frecventa.keySet()) {
            if (frecventa.get(departament) > max) {
                max = frecventa.get(departament);
            }
        }


        String rezultat = "Frecvente afecțiuni: ";
        for (String departament : frecventa.keySet()) {
            int valoare = frecventa.get(departament);
            if (max - valoare <= 1) {
                rezultat += departament + " (" + valoare + "), ";
            }
        }

        if (rezultat.endsWith(", ")) {
            rezultat = rezultat.substring(0, rezultat.length() - 2);
        }

        return rezultat;
    }


    public static DefaultTableModel istoricServicii(IstoricPacient istoricPacient) {

        String[] coloane = {"Data", "Tip Serviciu", "Descriere"};
        List<ServiciuMedical> serviciiMedicale = istoricPacient.getServiciiMedicale();
        DefaultTableModel model = new DefaultTableModel(coloane, 0);

        for (ServiciuMedical serviciu : serviciiMedicale) {
            String data = serviciu.getData().toString();
            String tip = serviciu instanceof Consultatie ? "Consultatie" : "Internare";
            String descriere = serviciu.getDescriere();
            model.addRow(new Object[]{data, tip, descriere});
        }

       return model;
    }

}
