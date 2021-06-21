package part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        String urlConnection = "jdbc:mysql://localhost/digital_innovation_one";
        Connection conn = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(urlConnection, "root", "password");
            System.out.println("Sucesso!");
        } catch (SQLException e) {
            System.out.println("FALHA!");
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        } finally {
            if (conn != null) {

                conn.close();
                System.out.println("Conexão fechada");
            }
        }

    }

}
