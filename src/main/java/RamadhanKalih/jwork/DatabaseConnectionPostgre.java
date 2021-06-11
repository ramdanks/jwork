package RamadhanKalih.jwork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnectionPostgre {

    private static Connection c = null;

    public static Connection connection() {      
        if (c == null)
        {
            final String db_name = "jwork";
            final String db_user = "postgres";
            final String db_password = "";
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + db_name, db_user, db_password);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getClass().getName() + ": " + e);
                System.exit(0);
            }
        }
        return c;
    }

}
