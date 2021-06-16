package RamadhanKalih.jwork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** kelas untuk membangun koneksi dengan dbms psql
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210617
 */
public class DatabaseConnectionPostgre
{
    /** menyimpan koneksi yang terbangun melalui metode connection() */
    private static Connection c = null;
    
    /** Membangun koneksi dengan server dbms psql apabila belum pernah di-inisialiasi
     * atau koneksi sudah pernah dibangun tapi sudah tertutup
     * @return Connection yang terbuka
     * @throws SQLException error akses ke dbms
     */
    public static Connection connection() throws SQLException {   
        if (c == null || c.isClosed())
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
