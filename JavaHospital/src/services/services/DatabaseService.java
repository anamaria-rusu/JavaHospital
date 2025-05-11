package services.services;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entities.DatabaseConnection;

public interface DatabaseService<T>
{
    String getTableName();
    T mapResultSetToEntity(ResultSet rs) throws SQLException;

    String getInsertQuery();
    String getUpdateQuery();
    String getDeleteQuery();

    void setParameters(PreparedStatement stmt, T entity, String operatie) throws SQLException;
    String getIdColumnName();


    default List<T> read()
    {
        List<T> rezultat = new ArrayList();
        String sql = "SELECT * FROM " + getTableName();

        try(Connection connection = DatabaseConnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next())
                rezultat.add(mapResultSetToEntity(rs));

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return rezultat;
    }

    default void create(T entity)
    {
        String sql = getInsertQuery();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            setParameters(stmt, entity,"create");
            stmt.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    default void update(T entity)
    {
        String sql = getUpdateQuery();

        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)){

            setParameters(stmt, entity,"update");
            stmt.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    default void delete(T entity) {
        String sql = getDeleteQuery();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            setParameters(stmt, entity, "delete");
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    default T cautaEntitate(int id) {
        String sql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + " = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToEntity(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}


