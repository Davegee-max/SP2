package Data.EKG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnector {
    private static Connection connection;
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:mysql://db.diplomportal.dk/s145857?" +
                        "user=s145857&password=zGZysXulb9aGAEj06dRMv");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}