package services.services;
import entities.Pacient;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.text.similarity.LevenshteinDistance;
//https://commons.apache.org/proper/commons-text/apidocs/org/apache/commons/text/similarity/LevenshteinDistance.html


public class PacientiServices implements PersoanaServices<Pacient>, DatabaseService<Pacient>
{
    private static PacientiServices pacientiServices;

    private PacientiServices() {}

    public static PacientiServices getPacientiServices() {
        if (pacientiServices == null)
            pacientiServices = new PacientiServices();
        return pacientiServices;
    }

    @Override
    public String getTableName() {
        return "pacienti";
    }

    @Override
    public String getIdColumnName() {
        return "idPacient";
    }

    @Override
    public Pacient mapResultSetToEntity(ResultSet rs) throws SQLException {
        int idPacient = rs.getInt("idPacient");
        String nume = rs.getString("nume");
        String prenume = rs.getString("prenume");
        LocalDate dataNasterii = rs.getDate("dataNasterii").toLocalDate();
        String telefon = rs.getString("telefon");
        String email = rs.getString("email");

        return new Pacient(idPacient, nume, prenume, dataNasterii, telefon, email);
    }

    @Override
    public void setParameters(PreparedStatement stmt, Pacient pacient, String operatie) throws SQLException {
        if(operatie.equals("delete"))
            stmt.setInt(1,pacient.getId());
        else
        {
            stmt.setString(1, pacient.getNume());
            stmt.setString(2, pacient.getPrenume());
            stmt.setDate(3, java.sql.Date.valueOf(pacient.getDataNasterii()));
            stmt.setString(4, pacient.getTelefon());
            stmt.setString(5, pacient.getEmail());
            if (operatie.equals("update"))
                stmt.setInt(6, pacient.getId());
        }

    }

    public List<Pacient> getPersoane() {
        return read();
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO " + getTableName() + " (nume, prenume, dataNasterii, telefon, email) VALUES (?, ?, ?, ?, ?)";
    }

    public void adaugaPacient(String nume, String prenume, LocalDate dataNasterii, String telefon, String email) {
        try {
            Pacient pacient = new Pacient(-1, nume, prenume, dataNasterii, telefon, email);
            create(pacient);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE " + getTableName() + " SET nume = ?, prenume = ?, dataNasterii = ?, telefon = ?, email = ? WHERE idPacient = ?";
    }

    public void actualizeazaPacient(int id,String nume, String prenume, LocalDate dataNasterii, String telefon, String email)
    {
        Pacient pacientUpdate = cautaEntitate(id);
        pacientUpdate.setNume(nume);
        pacientUpdate.setPrenume(prenume);
        pacientUpdate.setDataNasterii(dataNasterii);
        pacientUpdate.setTelefon(telefon);
        pacientUpdate.setEmail(email);
        update(pacientUpdate);

    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM " + getTableName() + " WHERE idPacient = ?";
    }

    public void stergePacient(int id) {
        Pacient pacientDelete = cautaEntitate(id);
        delete(pacientDelete);
    }
}






