package RamadhanKalih.jwork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/** kelas database menggunakan dbms psql untuk menyimpan Invoice
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210617
 */
public class DatabaseInvoicePostgre
{
    /** query psql memasukkan Invoice ke db */
    private static final String Q_INSERT        = "INSERT INTO invoice(job_id,date,totalfee,jobseeker_id,status,bonus_id,adminfee,ptype) VALUES (?,?,?,?,?,?,?,?) RETURNING id";
    /** query psql menghapus Invoice dari db */
    private static final String Q_REMOVE        = "DELETE FROM invoice WHERE id=?";
    /** query psql meminta seluruh Invoice dari db */
    private static final String Q_GET_ALL       = "SELECT * FROM invoice";
    /** query psql meminta Invoice dari db melalui nomor id */
    private static final String Q_GET_ID        = "SELECT * FROM invoice WHERE id=?";
    /** query psql meminta Invoice dari db melalui nomor jobseeker id */
    private static final String Q_GET_JS        = "SELECT * FROM invoice WHERE jobseeker_id=?";
    /** query psql memperbarui status Invoice dari db melalui nomor id */
    private static final String Q_ALTER_STATUS  = "UPDATE invoice SET status=? WHERE id=?";
    /** query psql meminta nomor id Invoice terakhir dalam db */
    private static final String Q_LASTID        = "SELECT id FROM invoice ORDER BY id DESC LIMIT 1";
    /** query psql menanyakan keberadaan Invoice dalam db dengan job id dan InvoiceStatus tertentu*/
    private static final String Q_EXIST_STATUS  = "SELECT EXISTS(SELECT 1 FROM invoice WHERE job_id=? AND status=?)";

    /** memasukkan ke dalam database psql
     * @param invoice Invoice yang ingin disimpan
     * @return nomor id dari Invoice yang tercatat dalam psql
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static int insertInvoice(Invoice invoice) throws Exception {
        int bonusId = invoice.bonus == null ? -1 : invoice.bonus.getId();
        return insertInvoice(
            invoice.getJobs().get(0).getId(),
            invoice.getDate(),
            invoice.getTotalFee(),
            invoice.getJobseeker().getID(),
            invoice.getInvoiceStatus(),
            bonusId,
            invoice.adminFee,
            invoice.type);
    }

    /** memasukkan ke dalam database psql
     * @param jobId job id Invoice
     * @param date date Invoice
     * @param totalFee total fee Invoice
     * @param jobseekerId jobseeker (pemilik) Invoice
     * @param status status Invoice
     * @param bonusId bonus id Invoice
     * @param adminFee admin fee Invoice
     * @param type tipe pembyaran Invoice
     * @return nomor id dari Invoice yang tercatat dalam psql
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static int insertInvoice(int jobId, Calendar date, int totalFee,
                                    int jobseekerId, InvoiceStatus status,
                                    int bonusId, int adminFee, PaymentType type) throws Exception {   
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_INSERT);
        // set null if bonus is invalid number
        if (bonusId < 0)    ps.setNull(6, java.sql.Types.INTEGER);
        else                ps.setInt(6, bonusId);
        ps.setInt(1, jobId);
        ps.setDate(2, new Date(date.getTimeInMillis()));
        ps.setInt(3, totalFee);
        ps.setInt(4, jobseekerId);
        ps.setString(5, status.toString());
        ps.setInt(7, adminFee);
        ps.setString(8, type.toString());
        ResultSet rs = ps.executeQuery();
        return rs.next() ? rs.getInt(1) : -1;
    }

    /** menghapus Invoice dari database psql
     * @param id nomor id dari Invoice
     * @return true jika berhasil menghapus sesuai parameter
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static boolean removeInvoice(int id) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_REMOVE);
        ps.setInt(1, id);
        return ps.executeUpdate() == 1;
    }

    /** meminta Invoice dari database psql
     * @param id nomor id dari Invoice
     * @return true jika ditemukan dalam database
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static Invoice getInvoice(int id) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GET_ID);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return toInvoice(rs);
        return null;
    }

    /** meminta seluruh Invoice dari database psql
     * @return Invoice yang tercatat atau diterima oleh API
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static ArrayList<Invoice> getAllInvoice() throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GET_ALL);
        ResultSet rs = ps.executeQuery();   
        ArrayList<Invoice> list = new ArrayList<>();
        while (rs.next())
            list.add(toInvoice(rs));
        return list;
    }

    /** meminta seluruh Invoice dari database psql melalui jobseeker
     * @param id nomor id dari jobseeker
     * @return list Invoice yang terikat dengan jobseeker parameter
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static ArrayList<Invoice> getInvoiceByJobseeker(int id) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GET_JS);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        ArrayList<Invoice> list = new ArrayList<>();
        while (rs.next())
            list.add(toInvoice(rs));
        return list;
    }

    /** meminta Invoice id terakhir dalam database
     * @return id Invoice, jika tidak ada return -1
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

    /** mengubah status Invoice dalam database
     * @return true jika berhasil mengubah, false jika tidak
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static boolean changeInvoiceStatus(int id, InvoiceStatus status) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_ALTER_STATUS);
        ps.setString(1, status.toString());
        ps.setInt(2, id);
        return ps.executeUpdate() == 1;
    }

    /** mengecek apakah job dengan status tertentu ada dalam database
     * @param id nomor id Job
     * @param status status invoice dari Job
     * @return true jika ditemukan, false jika tidak
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static boolean isJobExist(int id, InvoiceStatus status) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_EXIST_STATUS);
        ps.setInt(1, id);
        ps.setString(2, status.toString());
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return rs.getBoolean(1);
        return false;
    }
    
    /** mengubah result set dari API menjadi objek Invoice. Tidak dengan otomatis
     * memperbarui baris, panggil rs.next() terlebih dahulu untuk menyesuaikan baris
     * @param rs hasil dari java.sql.PreparedStatement.executeQuery()
     * @return Invoice yang sesuai
     * @throws Exception error mengurai data ResultSet, mengambil Job dari database,
     * mengambil Jobseeker dari database, atau meminta Bonus dari database.
     */
    private static Invoice toInvoice(ResultSet rs) throws Exception {

        Job job = DatabaseJobPostgre.getJob(rs.getInt("job_id"));
        ArrayList<Job> joblist = new ArrayList<Job>() {{ add(job); }};
        Jobseeker js = DatabaseJobseekerPostgre.getJobseeker(rs.getInt("jobseeker_id"));
        
        Invoice inv = null;
        PaymentType type = PaymentType.fromString(rs.getString("ptype").stripTrailing());
        if (type == PaymentType.BankPayment)
        {
            BankPayment p = new BankPayment(rs.getInt("id"), joblist, js);
            p.setAdminFee(rs.getInt("adminfee"));
            inv = p;
        }
        else if (type == PaymentType.EwalletPayment)
        {
            Bonus bonus = DatabaseBonusPostgre.getBonus(rs.getInt("bonus_id"));
            EwalletPayment p = new EwalletPayment(rs.getInt("id"), joblist, js);
            p.setBonus(bonus);
            inv = p;
        }

        
        Date date = rs.getDate("date");
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        inv.setDate(cal);

        InvoiceStatus status = InvoiceStatus.fromString(rs.getString("status").stripTrailing());
        inv.setInvoiceStatus(status);

        return inv;
    }
}
