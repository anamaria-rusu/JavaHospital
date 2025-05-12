package services.services;
import entities.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;



public class InternareServices implements DatabaseService<Internare>
{
    private static InternareServices internareServices;
    private InternareServices(){}

    public static InternareServices getInternareServices() {
        if(internareServices==null)
            internareServices = new InternareServices();
        return internareServices;
    }

    @Override
    public String getTableName() {
        return "internari";
    }

    @Override
    public String getIdColumnName() {
        return "idInternare";
    }



    @Override
    public Internare mapResultSetToEntity (ResultSet rs) throws SQLException{
        int idInternare = rs.getInt("idInternare");
        int idPacient = rs.getInt("idPacient");
        Pacient pacient = PacientiServices.getPacientiServices().cautaEntitate(idPacient);
        int idSalon = rs.getInt("idSalon");
        Salon salon = SalonServices.getSalonServices().cautaEntitate(idSalon);
        LocalDate dataInternare = rs.getDate("dataInternare").toLocalDate();
        String departamentMedical = rs.getString("departamentMedical");
        String diagnostic = rs.getString("diagnostic");

        return new Internare(idInternare,pacient,salon,diagnostic,departamentMedical,dataInternare);

    }

    @Override
    public void setParameters(PreparedStatement stmt, Internare internare, String operatie) throws SQLException {
        if(operatie.equals("delete"))
            stmt.setInt(1,internare.getId());
        else if (operatie.equals("create"))
        {
            stmt.setInt(1, internare.getPacient().getId());
            stmt.setInt(2, internare.getSalon().getId());
            stmt.setDate(3, java.sql.Date.valueOf(internare.getData()));
            stmt.setString(4, internare.getDepartamentMedical());
            stmt.setString(5, internare.getDiagnostic());

        }
        else if (operatie.equals("update"))
        {
            stmt.setInt(1, internare.getSalon().getId());
            stmt.setString(2, internare.getDepartamentMedical());
            stmt.setString(3, internare.getDiagnostic());
            stmt.setInt(4,internare.getId());
        }
    }


    public List<Internare> getPacientiInternati(Salon salon)
    {
        List<Internare> internari = read();
        List<Internare> pacientiInternati = new ArrayList<>();
        for (Internare internare : internari){
            if(internare.getSalon().getId() == salon.getId()) {
                pacientiInternati.add(internare);
            }
        }
        return pacientiInternati;
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO " + getTableName() + " (idPacient, idSalon, dataInternare, departamentMedical, diagnostic) VALUES (?, ?, ?, ?, ?)";
    }

    public Internare internarePacient(Pacient pacient, Salon salon, String diagnostic, String departament) {
        Internare internareNoua = new Internare(-1,pacient,salon,diagnostic,departament);
        create(internareNoua);
        //pacient.adaugaIstoric(internareNoua);
        //salon.setCapacitateCurenta(salon.getCapacitateCurenta()+1);
        return internareNoua;
    }


    @Override
    public String getUpdateQuery() {
        return "UPDATE " + getTableName() + " SET idSalon = ?,  departamentMedical = ?,  diagnostic = ? WHERE idInternare = ?";
    }

    public void actualizeazaInternare(int id, int idSalon, String departamentMedical, String diagnostic)
    {
        Internare  internareUpdate = cautaEntitate(id);
        Salon salon = SalonServices.getSalonServices().cautaEntitate(idSalon);
        internareUpdate.setSalon(salon);
        internareUpdate.setDepartamentMedical(departamentMedical);
        internareUpdate.setDiagnostic(diagnostic);
        update(internareUpdate);
    }


    @Override
    public String getDeleteQuery() {
        return "DELETE FROM " + getTableName() + " WHERE idInternare = ?";
    }

    public void stergeInternare(int id) {
        Internare internareDelete = cautaEntitate(id);
        delete(internareDelete);
    }

}
