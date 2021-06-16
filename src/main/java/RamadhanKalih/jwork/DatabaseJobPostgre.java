package RamadhanKalih.jwork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

/** kelas database menggunakan dbms psql untuk menyimpan Job
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210617
 */
public class DatabaseJobPostgre
{
    /** query psql memasukkan Job ke db */
    private static final String Q_INSERT    = "INSERT INTO job(name,recruiter_id,fee,category) VALUES (?,?,?,?) RETURNING id";
    /** query psql menghapus Job dari db */
    private static final String Q_REMOVE    = "DELETE FROM job WHERE id=?";
    /** query psql meminta seluruh Job dari db */
    private static final String Q_GET_ALL   = "SELECT * FROM job";
    /** query psql meminta Job dari db melalui nomor id */
    private static final String Q_GET_ID    = "SELECT * FROM job WHERE id=?";
    /** query psql meminta Job dari db melalui nomor recruiter id */
    private static final String Q_GET_REC   = "SELECT * FROM job WHERE recruiter_id=?";
    /** query psql meminta Job dari db melalui JobCategory */
    private static final String Q_GET_CAT   = "SELECT * FROM job WHERE category=?";
    /** query psql meminta nomor id Job terakhir dalam db */
    private static final String Q_LASTID    = "SELECT id FROM job ORDER BY id DESC LIMIT 1";

    /** memasukkan ke dalam database psql
     * @param job Job yang ingin disimpan
     * @return nomor id dari Job yang tercatat dalam psql
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static int insertJob(Job job) throws Exception {   
        return insertJob(
            job.getName(),
            job.getRecruiter().getId(),
            job.getFee(),
            job.getCategory());
    }

    /** memasukkan ke dalam database psql
     * @param name nama Job
     * @param recruiterId recruiter id Job
     * @param fee fee Job
     * @param category kategori job
     * @return nomor id dari Job yang tercatat dalam psql
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static int insertJob(String name, int recruiterId, int fee, JobCategory category) throws Exception {   
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_INSERT);
        ps.setString(1, name);
        ps.setInt(2, recruiterId);
        ps.setInt(3, fee);
        ps.setString(4, category.toString());
        ResultSet rs = ps.executeQuery();
        return rs.next() ? rs.getInt(1) : -1;
    }

    /** menghapus Job dari database psql
     * @param id nomor id dari Job
     * @return true jika berhasil menghapus sesuai parameter
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static boolean removeJob(int id) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_REMOVE);
        ps.setInt(1, id);
        return ps.executeUpdate() == 1;
    }

    /** meminta Job dari database psql
     * @param id nomor id dari Job
     * @return true jika ditemukan dalam database
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static Job getJob(int id) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GET_ID);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return toJob(rs);
        return null;
    }

    /** meminta seluruh Job dari database psql
     * @return Job yang tercatat atau diterima oleh API
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static ArrayList<Job> getAllJob() throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GET_ALL);
        ResultSet rs = ps.executeQuery();
        ArrayList<Job> list = new ArrayList<>();
        while (rs.next())
            list.add(toJob(rs));
        return list;
    }

    /** meminta seluruh Job dari database psql melalui recruiter
     * @param id nomor id dari recruiter
     * @return list Job yang terikat dengan recruiter parameter
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static ArrayList<Job> getJobByRecruiter(int id) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GET_REC);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        ArrayList<Job> list = new ArrayList<>();
        while (rs.next())
            list.add(toJob(rs));
        return list;
    }

    /** meminta seluruh Job dari database psql melalui JobCategory
     * @param category kategori job
     * @return list Job yang terikat dengan kategori parameter
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static ArrayList<Job> getJobByCategory(JobCategory category) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GET_CAT);
        ps.setString(1, category.toString());
        ResultSet rs = ps.executeQuery();
        ArrayList<Job> list = new ArrayList<>();
        while (rs.next())
            list.add(toJob(rs));
        return list;
    }

    /** meminta Job id terakhir dalam database
     * @return id Job, jika tidak ada return -1
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static int getLastJobId() throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_LASTID);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return rs.getInt(1);
        return -1;
    }
    
    /** mengubah result set dari API menjadi objek Job. Tidak dengan otomatis
     * memperbarui baris, panggil rs.next() terlebih dahulu untuk menyesuaikan baris
     * @param rs hasil dari java.sql.PreparedStatement.executeQuery()
     * @return Job yang sesuai
     * @throws Exception error mengurai data ResultSet atau mengambil jobseeker dari dbms
     */
    private static Job toJob(ResultSet rs) throws Exception {
        Recruiter rec = DatabaseRecruiterPostgre.getRecruiter(rs.getInt("recruiter_id"));
        JobCategory cat = JobCategory.fromString(rs.getString("category").stripTrailing());
        return new Job(
            rs.getInt("id"),
            rs.getString("name").stripTrailing(),
            rec,
            rs.getInt("fee"),
            cat
        );
    }
}
