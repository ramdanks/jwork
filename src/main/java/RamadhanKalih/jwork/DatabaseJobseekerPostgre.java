package RamadhanKalih.jwork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

/** kelas database menggunakan dbms psql untuk menyimpan Jobseeker
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210617
 */
public class DatabaseJobseekerPostgre
{
    /** query psql memasukkan Jobseeker ke db */
    private static final String Q_INSERT    = "INSERT INTO jobseeker(name,email,password) VALUES (?,?,?) RETURNING id";
    /** query psql menghapus Jobseeker dari db */
    private static final String Q_REMOVE    = "DELETE FROM jobseeker WHERE id=?";
    /** query psql meminta Jobseeker dari db melalui nomor id */
    private static final String Q_GET1      = "SELECT * FROM jobseeker WHERE id=?";
    /** query psql meminta seluruh Jobseeker dari db melalui email dan password */
    private static final String Q_GET2      = "SELECT * FROM jobseeker WHERE email=? AND password=?";
    /** query psql meminta nomor id Jobseeker terakhir dalam db */
    private static final String Q_GETLASTID = "SELECT id FROM jobseeker ORDER BY id DESC LIMIT 1";

    /** memasukkan ke dalam database psql
     * @param jobseeker Jobseeker yang ingin disimpan
     * @return nomor id dari Jobseeker yang tercatat dalam psql
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static int insertJobseeker(Jobseeker jobseeker) throws Exception {
        return insertJobseeker(
            jobseeker.getName(),
            jobseeker.getEmail(),
            jobseeker.getPassword());
    }

    /** memasukkan ke dalam database psql
     * @param name nama Jobseeker
     * @param email email Jobseeker
     * @param password password Jobseeker
     * @return nomor id dari Jobseeker yang tercatat dalam psql
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static int insertJobseeker(String name, String email, String password) throws Exception {   
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_INSERT);
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, password);
        ResultSet rs = ps.executeQuery();
        return rs.next() ? rs.getInt(1) : -1;
    }

    /** menghapus Jobseeker dari database psql
     * @param id nomor id dari Jobseeker
     * @return true jika berhasil menghapus sesuai parameter
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static boolean removeJobseeker(int id) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_REMOVE);
        ps.setInt(1, id);
        return ps.executeUpdate() == 1;
    }

    /** meminta Jobseeker dari database psql
     * @param id nomor id dari Jobseeker
     * @return true jika ditemukan dalam database
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static Jobseeker getJobseeker(int id) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GET1);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return toJobseeker(rs);
        return null;
    }

    /** meminta Jobseeker dari database psql
     * @param email email dari Jobseeker
     * @param password password yang terdaftar dengan email tsb
     * @return true jika ditemukan dalam database
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static Jobseeker getJobseeker(String email, String password) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GET2);
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return toJobseeker(rs);
        return null;
    }

    /** meminta Jobseeker id terakhir dalam database
     * @return id Jobseeker, jika tidak ada return -1
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static int getLastJobseekerId() throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GETLASTID);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return rs.getInt(1);
        return -1;
    }
    
    /** mengubah result set dari API menjadi objek Jobseeker. Tidak dengan otomatis
     * memperbarui baris, panggil rs.next() terlebih dahulu untuk menyesuaikan baris
     * @param rs hasil dari java.sql.PreparedStatement.executeQuery()
     * @return Jobseeker yang sesuai
     * @throws Exception error mengurai data ResultSet
     */
    private static Jobseeker toJobseeker(ResultSet rs) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(rs.getTimestamp("creationtime"));
        return new Jobseeker(
            rs.getInt("id"),
            rs.getString("name").stripTrailing(),
            rs.getString("email").stripTrailing(),
            rs.getString("password").stripTrailing(),
            cal
        );
    }
}
