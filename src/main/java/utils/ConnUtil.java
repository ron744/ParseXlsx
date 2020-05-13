package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnUtil {

    public static Connection getNewConnection() throws SQLException, ClassNotFoundException {
        String userName = "root";
        String password = "root";
        String db = "efko";

        Class.forName("com.mysql.cj.jdbc.Driver");

        String connectionURL = "jdbc:mysql://localhost:3306/" + db;

        return DriverManager.getConnection(connectionURL, userName, password);
    }
}
