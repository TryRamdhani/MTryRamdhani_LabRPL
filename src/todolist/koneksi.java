
package todolist;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class koneksi {
    private static final String URL = "jdbc:mysql://localhost:4306/todolistlabti";
    private static final String USER = "root";            
    private static final String PASSWORD = "";          

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initializeDatabase() {
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS tugas (id INT AUTO_INCREMENT PRIMARY KEY, judul VARCHAR(255), deadline DATE, status BOOLEAN DEFAULT 0)")) {
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Database Initialization Error: " + e.getMessage());
        }
    }
}
