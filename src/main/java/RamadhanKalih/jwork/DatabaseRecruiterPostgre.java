package RamadhanKalih.jwork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;


/** kelas database menggunakan dbms psql untuk menyimpan Recruiter
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210617
 */
public class DatabaseRecruiterPostgre 
{
    /** query psql memasukkan Recruiter ke db */
    private static final String Q_INSERT    = "INSERT INTO recruiter(name,email,phone_number,loc_province,loc_city,loc_desc) VALUES (?,?,?,?,?,?) RETURNING id";
    /** query psql menghapus Recruiter dari db */
    private static final String Q_REMOVE    = "DELETE FROM recruiter WHERE id=?";
    /** query psql meminta Recruiter dari db melalui nomor id */
    private static final String Q_GET       = "SELECT * FROM recruiter WHERE id=?";
    /** query psql meminta seluruh Recruiter dari db */
    private static final String Q_GETALL    = "SELECT * FROM recruiter";
    /** query psql meminta nomor id Recruiter terakhir dalam db */
    private static final String Q_GETLASTID = "SELECT id FROM recruiter ORDER BY id DESC LIMIT 1";

    /** memasukkan ke dalam database psql
     * @param recruiter Recruiter yang ingin disimpan
     * @return nomor id dari recruiter yang tercatat dalam psql
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static int insertRecruiter(Recruiter recruiter) throws Exception {   
        return insertRecruiter(
            recruiter.getName(),
            recruiter.getEmail(),
            recruiter.getPhoneNumber(),
            recruiter.getLocation().getProvince(),
            recruiter.getLocation().getCity(),
            recruiter.getLocation().getDescription());
    }

    /** memasukkan ke dalam database psql
     * @param name nama recruiter
     * @param email email recruiter
     * @param phoneNumber nomor telepon recruiter
     * @param loc_province lokasi provinsi si recruiter
     * @param loc_city lokasi kota si recruiter
     * @param loc_desc deksripsi lokasi si recruiter
     * @return nomor id dari recruiter yang tercatat dalam psql
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static int insertRecruiter(  String name, String email, String phoneNumber,
                                        String loc_province, String loc_city, String loc_desc) throws Exception {   
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_INSERT);
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, phoneNumber);
        ps.setString(4, loc_province);
        ps.setString(5, loc_city);
        ps.setString(6, loc_desc);
        ResultSet rs = ps.executeQuery();
        return rs.next() ? rs.getInt(1) : -1;
    }

    /** menghapus recruiter dari database psql
     * @param id nomor id dari recruiter
     * @return true jika berhasil menghapus sesuai parameter
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static boolean removeRecruiter(int id) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_REMOVE);
        ps.setInt(1, id);
        return ps.executeUpdate() == 1;
    }

    /** meminta recruiter dari database psql
     * @param id nomor id dari recruiter
     * @return true jika ditemukan dalam database
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static Recruiter getRecruiter(int id) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GET);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return toRecruiter(rs);
        return null;
    }

    /** meminta seluruh recruiter dari database psql
     * @return recruiter yang tercatat atau diterima oleh API
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static ArrayList<Recruiter> getAllRecruiter() throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GETALL);
        ResultSet rs = ps.executeQuery();
        ArrayList<Recruiter> list = new ArrayList<>();
        while (rs.next())
            list.add(toRecruiter(rs));
        return list;
    }

    /** meminta recruiter id terakhir dalam database
     * @return id recruiter, jika tidak ada return -1
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static int getLastRecruiterId() throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GETLASTID);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return rs.getInt(1);
        return -1;
    }
    
    /** mengubah result set dari API menjadi objek Recruiter. Tidak dengan otomatis
     * memperbarui baris, panggil rs.next() terlebih dahulu untuk menyesuaikan baris
     * @param rs hasil dari java.sql.PreparedStatement.executeQuery()
     * @return Recruiter yang sesuai
     * @throws Exception error mengurai data ResultSet
     */
    private static Recruiter toRecruiter(ResultSet rs) throws Exception {
        Location loc = new Location(
            rs.getString("loc_province").stripTrailing(),
            rs.getString("loc_city").stripTrailing(),
            rs.getString("loc_desc").stripTrailing()
        );
        return new Recruiter(
            rs.getInt("id"),
            rs.getString("name").stripTrailing(),
            rs.getString("email").stripTrailing(),
            rs.getString("phone_number").stripTrailing(),
            loc
        );
    }
}
