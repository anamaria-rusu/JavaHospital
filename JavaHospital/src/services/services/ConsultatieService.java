package services.services;
import entities.Medic;
import entities.Pacient;
import entities.SugestieProgramare;
import entities.Consultatie;

import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.*;

public class ConsultatieService {

    private List<Consultatie> consultatii = new ArrayList<>();
    private MediciServices mediciServices;

    public ConsultatieService(MediciServices mediciServices) {
        this.mediciServices = mediciServices;
    }



    public List<SugestieProgramare> mediciDisponibiliConsultatie(LocalDate data, LocalTime oraDorita, String departament, int durataMinute) {
        List<Medic> totiMedicii = mediciServices.getPersoane();
        List<SugestieProgramare> sugestii = new ArrayList<>();
        Map<Medic, Integer> medicProgramariCount = new HashMap<>();

        for (Medic medic : totiMedicii) {
            if (!medic.getDepartamentMedical().equals(departament)) {
                continue;
            }

            // 1. Adunăm toate consultațiile medicului în ziua respectivă +m inceut pogram

            List<Consultatie> programari = new ArrayList<>();
            programari.add(new Consultatie(medic, null, departament, data, LocalTime.of(8, 0), 0, "")); // Începutul zilei

            for (Consultatie c : consultatii) {
                if (c.getMedic().equals(medic) && c.getData().equals(data)) {
                    programari.add(c);
                }
            }

            // 2. Adăugăm începutul și sfârșitul programului
            programari.add(new Consultatie(medic, null, departament, data, LocalTime.of(16, 0), 0, "")); // Sfârșitul zilei

            // 3. Sortăm consultațiile după ora
            Collections.sort(programari, Comparator.comparing(Consultatie::getOraProgramare));

            LocalTime oraFinala = null;
            long minDiferenta = Long.MAX_VALUE;

            // 4. Verificăm spațiile între consultații
            for (int i = 0; i < programari.size() - 1; i++) {
                Consultatie curenta = programari.get(i);
                Consultatie urmatoare = programari.get(i + 1);

                LocalTime sfarsitCurenta = curenta.getOraProgramare().plusMinutes(curenta.getDurataConsultatie()); // c[i] + d[i]
                LocalTime inceputUrmatoare = urmatoare.getOraProgramare(); // c[i+1]


                // AICI - verificare dacă ora dorită se încadrează între două consultații
                if (oraDorita.isAfter(sfarsitCurenta) && oraDorita.plusMinutes(durataMinute).isBefore(inceputUrmatoare)) {
                    oraFinala = oraDorita;
                    break;
                }

                long spatiuLiber = Duration.between(sfarsitCurenta, inceputUrmatoare).toMinutes();
                if (spatiuLiber >= durataMinute) {
                    long diferenta = Math.abs(Duration.between(oraDorita, sfarsitCurenta).toMinutes());
                    if (diferenta < minDiferenta) {
                        minDiferenta = diferenta;
                        oraFinala = sfarsitCurenta;
                    }
                }
            }

            // 5. Dacă am găsit o oră validă
            if (oraFinala != null) {
                sugestii.add(new SugestieProgramare(medic, data, oraFinala));
                medicProgramariCount.put(medic,programari.size());

            }
        }


        sugestii.sort(Comparator.comparing(s -> medicProgramariCount.get(s.medic())));
        return sugestii;
    }



    public void adaugaConsultatie(Medic medic, Pacient pacient, String departament, LocalDate dataProgramare, LocalTime oraProgramare, int durataConsultatie, String motiv)
    {
        Consultatie consultatie = new Consultatie(medic,pacient,departament,dataProgramare,oraProgramare,durataConsultatie,motiv);
        consultatii.add(consultatie);
        pacient.adaugaIstoric(consultatie);
    }

    public List<Consultatie> getConsultatii() {
        return new ArrayList<>(consultatii);
    }



}
