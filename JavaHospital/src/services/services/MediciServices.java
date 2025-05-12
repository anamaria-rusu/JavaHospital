package services.services;

import entities.Medic;
import entities.Pacient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MediciServices implements PersoanaServices<Medic>, DatabaseService<Medic>{

    private static MediciServices mediciServices;

    private MediciServices(){}

    public static  MediciServices getMediciServices(){
        if(mediciServices == null)
            mediciServices = new MediciServices();
        return mediciServices;

    }

    @Override
    public String getTableName() {
        return "medici";
    }

    @Override
    public String getIdColumnName() {
        return "idMedic";
    }

    @Override
    public Medic mapResultSetToEntity(ResultSet rs) throws SQLException {
        int idMedic = rs.getInt("idMedic");
        String nume = rs.getString("nume");
        String prenume = rs.getString("prenume");
        LocalDate dataNasterii = rs.getDate("dataNasterii").toLocalDate();
        String telefon = rs.getString("telefon");
        String email = rs.getString("email");
        LocalDate dataAngajarii = rs.getDate("dataAngajarii").toLocalDate();
        String departamentMedical = rs.getString("departamentMedical");

        return new Medic(idMedic, nume, prenume, dataNasterii, telefon, email, dataAngajarii,departamentMedical);
    }

    @Override
    public void setParameters(PreparedStatement stmt, Medic medic, String operatie) throws SQLException {
        if(operatie.equals("delete"))
            stmt.setInt(1,medic.getId());
        else
        {
            stmt.setString(1, medic.getNume());
            stmt.setString(2, medic.getPrenume());
            stmt.setDate(3, java.sql.Date.valueOf(medic.getDataNasterii()));
            stmt.setString(4, medic.getTelefon());
            stmt.setString(5, medic.getEmail());
            stmt.setDate(6,java.sql.Date.valueOf(medic.getDataAngajarii()));
            stmt.setString(7,medic.getDepartamentMedical());
            if (operatie.equals("update"))
                stmt.setInt(8, medic.getId());
        }
    }

    public List<Medic> getPersoane() {
        return read();
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO " + getTableName() + " (nume, prenume, dataNasterii, telefon, email, dataAngajarii, departamentMedical) VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    public void adaugaMedic(String nume, String prenume, LocalDate dataNasterii, String telefon, String email, LocalDate dataAngajarii, String departamentMedical) {
        try {
            Medic medic = new Medic(-1, nume, prenume, dataNasterii, telefon, email, dataAngajarii, departamentMedical);
            create(medic);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE " + getTableName() + " SET nume = ?, prenume = ?, dataNasterii = ?, telefon = ?, email = ?, dataAngajarii = ?, departamentMedical = ? WHERE idMedic = ?";
    }

    public void actualizeazaMedic(int id,String nume, String prenume, LocalDate dataNasterii, String telefon, String email, LocalDate dataAngajarii, String departamentMedical)
    {
        Medic medicUpdate = cautaEntitate(id);
        medicUpdate.setNume(nume);
        medicUpdate.setPrenume(prenume);
        medicUpdate.setDataNasterii(dataNasterii);
        medicUpdate.setTelefon(telefon);
        medicUpdate.setEmail(email);
        medicUpdate.setDataAngajarii(dataAngajarii);
        medicUpdate.setDepartamentMedical(departamentMedical);
        update(medicUpdate);
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM " + getTableName() + " WHERE idMedic = ?";
    }

    public void stergeMedic(int id) {
        Medic medicDelete = cautaEntitate(id);
        delete(medicDelete);
        CvsServices.log("Salvare internare");
    }

}
