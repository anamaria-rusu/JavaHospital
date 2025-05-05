import services.Login;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/javahospital";
        String user = "root";
        String password = "qwertyuiop0";

        try (
                Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM PERSOANA")
        ) {
            while (resultSet.next()) {
                System.out.println("Nume: " + resultSet.getString("nume"));
            }

            Login.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
