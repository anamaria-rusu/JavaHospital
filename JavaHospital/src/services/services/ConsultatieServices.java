package services.services;
import entities.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.*;

public class ConsultatieServices implements DatabaseService<Consultatie> {

    private static ConsultatieServices consultatieServices;

    private ConsultatieServices(){}

    public static ConsultatieServices getConsultatieServices(){
        if(consultatieServices==null)
            consultatieServices = new ConsultatieServices();
        return consultatieServices;
    }

    @Override
    public String getTableName() {
        return "consultatii";
    }

    @Override
    public String getIdColumnName(){
        return "idConsultatie";
    }

    @Override
    public Consultatie mapResultSetToEntity(ResultSet rs) throws SQLException {
        int idConsultatie = rs.getInt("idConsultatie");

        int idMedic = rs.getInt("idMedic");
        Medic medic = MediciServices.getMediciServices().cautaEntitate(idMedic);

        int idPacient = rs.getInt("idPacient");
        Pacient pacient = PacientiServices.getPacientiServices().cautaEntitate(idPacient);

        String departament = rs.getString("departament");
        LocalDate dataProgramare = rs.getDate("dataProgramare").toLocalDate();
        LocalTime oraProgramare= rs.getTime("oraProgramare").toLocalTime();
        int durataConsultatie = rs.getInt("durataConsultatie");
        String motiv = rs.getString("motiv");

        return new Consultatie(idConsultatie,medic, pacient,departament,dataProgramare,oraProgramare,durataConsultatie,motiv);
    }

    @Override
    public void setParameters(PreparedStatement stmt, Consultatie consultatie, String operatie) throws SQLException {
        if(operatie.equals("delete"))
            stmt.setInt(1,consultatie.getId());
        else if (operatie.equals("create"))
        {
            stmt.setInt(1, consultatie.getMedic().getId());
            stmt.setInt(2, consultatie.getPacient().getId());
            stmt.setString(3, consultatie.getDepartamentMedical());
            stmt.setDate(4, java.sql.Date.valueOf(consultatie.getData()));
            stmt.setTime(5,java.sql.Time.valueOf(consultatie.getOraProgramare()));
            stmt.setInt(6, consultatie.getDurataConsultatie());
            stmt.setString(7, consultatie.getMotiv());

        }
        else if (operatie.equals("update"))
        {
            stmt.setDate(1, java.sql.Date.valueOf(consultatie.getData()));
            stmt.setTime(2,java.sql.Time.valueOf(consultatie.getOraProgramare()));
            stmt.setInt(3, consultatie.getDurataConsultatie());
            stmt.setString(4, consultatie.getMotiv());
            stmt.setInt(5,consultatie.getId());
        }
    }

    public List<Consultatie> getConsultatii() {
        CvsServices.log("READ - Consultatii");
        return read();
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO " + getTableName() + " (idMedic, idPacient, departament, dataProgramare, oraProgramare, durataConsultatie, motiv) VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    public void adaugaConsultatie (Medic medic, Pacient pacient, String departament, LocalDate dataProgramare, LocalTime oraProgramare, int durataConsultatie , String motiv) {
        try {
            Consultatie consultatie = new Consultatie(-1, medic, pacient, departament, dataProgramare, oraProgramare, durataConsultatie, motiv);
            create(consultatie);
            CvsServices.log("CREATE - Consultatii");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE " + getTableName() + " SET dataProgramare = ?, oraProgramare = ?, durataConsultatie = ?, motiv = ? WHERE idConsultatie = ?";
    }

    public void actualizeazaConsultatie(int id, LocalDate dataProgramare, LocalTime oraProgramare, int durataConsultatie, String motiv) {
        try {
            Consultatie consultatieUpdate = cautaEntitate(id);

            if (consultatieUpdate == null) {
                throw new IllegalArgumentException("Consultația nu a fost găsită.");
            }

            if (dataProgramare == null || oraProgramare == null) {
                throw new IllegalArgumentException("Data și ora programării trebuie completate.");
            }

            if (dataProgramare.isBefore(LocalDate.now()) ||
                    (dataProgramare.isEqual(LocalDate.now()) && oraProgramare.isBefore(LocalTime.now()))) {
                throw new IllegalArgumentException("Nu poți programa o consultație în trecut.");
            }

            if (durataConsultatie <= 0) {
                throw new IllegalArgumentException("Durata consultației trebuie să fie mai mare decât 0.");
            }

            if (motiv == null || motiv.trim().isEmpty()) {
                throw new IllegalArgumentException("Trebuie să specifici un motiv pentru consultație.");
            }

            consultatieUpdate.setDataProgramare(dataProgramare);
            consultatieUpdate.setOraProgramare(oraProgramare);
            consultatieUpdate.setDurataConsultatie(durataConsultatie);
            consultatieUpdate.setMotiv(motiv);

            update(consultatieUpdate);
            CvsServices.log("UPDATE - Consultatii");
        } catch (IllegalArgumentException e) {
            System.out.println("Eroare: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Eroare neașteptată: " + e.getMessage());
        }
    }


    @Override
    public String getDeleteQuery() {
        return "DELETE FROM " + getTableName() + " WHERE idConsultatie = ?";
    }

    public void stergeConsultatie(int id) {
        try {
            Consultatie consultatieDelete = cautaEntitate(id);
            if (consultatieDelete == null) {
                throw new IllegalArgumentException("Consultația nu a fost găsită.");
            }

            delete(consultatieDelete);
            CvsServices.log("DELETE - Consultatii");
        } catch (IllegalArgumentException e) {
            System.out.println("Eroare: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Eroare neașteptată: " + e.getMessage());
        }
    }



    public boolean esteDisponibil(int id,Medic medic, LocalDate data, LocalTime oraInceput, int durataMinute) {
        List<Consultatie> consultatii = read();
        LocalTime oraSfarsit = oraInceput.plusMinutes(durataMinute);

        for (Consultatie c : consultatii) {
            if (c.getMedic().getId()!= medic.getId() || !c.getData().equals(data) || c.getId() == id)
                continue;


            LocalTime inceputExistenta = c.getOraProgramare();
            LocalTime sfarsitExistenta = inceputExistenta.plusMinutes(c.getDurataConsultatie());

            boolean seSuprapun = !oraSfarsit.isBefore(inceputExistenta) && !oraInceput.isAfter(sfarsitExistenta.minusMinutes(1));
            if (seSuprapun) {
                return false;
            }
        }

        return true;
    }

    public List<SugestieProgramare> mediciDisponibiliConsultatie(LocalDate data, LocalTime oraDorita, String departament, int durataMinute)
    {
        List<Medic> totiMedicii = MediciServices.getMediciServices().getPersoane();
        List<Consultatie> consultatii = read();
        List<SugestieProgramare> sugestii = new ArrayList<>();
        Map<Medic, Integer> medicProgramariCount = new HashMap<>();

        for (Medic medic : totiMedicii)
        {
            if (!medic.getDepartamentMedical().equals(departament)) {
                continue;
            }

            // 1. Adunam toate consultațiile medicului in ziua respectiva

            List<Consultatie> programari = new ArrayList<>();
            programari.add(new Consultatie(-1,medic, null, departament, data, LocalTime.of(8, 0), 0, ""));

            for (Consultatie c : consultatii) {
                if (c.getMedic().getId() == medic.getId() && c.getData().equals(data)) {
                    programari.add(c);
                }
            }

            // 2. Adăugam inceputul si sfarsitul programului
            programari.add(new Consultatie(-2,medic, null, departament, data, LocalTime.of(16, 0), 0, "")); // Sfârșitul zilei

            // 3. Sortam consultațiile dupa ora
            Collections.sort(programari, Comparator.comparing(Consultatie::getOraProgramare));

            LocalTime oraFinala = null;
            long minDiferenta = Long.MAX_VALUE;

            // 4. Verificam spațiile intre consultații
            for (int i = 0; i < programari.size() - 1; i++) {
                Consultatie curenta = programari.get(i);
                Consultatie urmatoare = programari.get(i + 1);

                LocalTime sfarsitCurenta = curenta.getOraProgramare().plusMinutes(curenta.getDurataConsultatie()); // c[i] + d[i]
                LocalTime inceputUrmatoare = urmatoare.getOraProgramare(); // c[i+1]

                //  verificare dacă ora dorita se incadrează intre doua consultații
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

            // 5. Daca am gasit o ora valida

            if (oraFinala != null) {
                sugestii.add(new SugestieProgramare(medic, data, oraFinala));
                medicProgramariCount.put(medic,programari.size());
            }
        }


        sugestii.sort(Comparator.comparing(s -> medicProgramariCount.get(s.medic())));
        return sugestii;
    }

}
