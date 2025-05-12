package entities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection
{
    private static final String url = "jdbc:mysql://localhost:3306/javahospital";
    private static final String user = "root";
    private static final String password = "qwertyuiop0";

    private DatabaseConnection() {}

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            return null;
        }
    }

}
