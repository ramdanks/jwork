package RamadhanKalih.jwork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

/** kelas database menggunakan dbms psql untuk menyimpan Bonus
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210617
 */
public class DatabaseBonusPostgre 
{
    /** query psql memasukkan Bonus ke db */
    private static final String Q_INSERT    = "INSERT INTO bonus(referralcode,extrafee,mintotalfee,active) VALUES (?,?,?,?) RETURNING id";
    /** query psql menghapus Bonus dari db */
    private static final String Q_REMOVE    = "DELETE FROM bonus WHERE id=?";
    /** query psql meminta seluruh Bonus dari db */
    private static final String Q_GET_ALL   = "SELECT * FROM bonus";
    /** query psql meminta Bonus dari db melalui nomor id */
    private static final String Q_GET_ID    = "SELECT * FROM bonus WHERE id=?";
    /** query psql meminta Bonus dari db melalui kode referral */
    private static final String Q_GET_REF   = "SELECT * FROM bonus WHERE referralcode=?";
    /** query psql meminta nomor id Bonus terakhir dalam db */
    private static final String Q_LASTID    = "SELECT id FROM bonus ORDER BY id DESC LIMIT 1";

    /** memasukkan ke dalam database psql
     * @param bonus Bonus yang ingin disimpan
     * @return nomor id dari Bonus yang tercatat dalam psql
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static int insertBonus(Bonus bonus) throws Exception {
        return insertBonus(
            bonus.getReferralCode(),
            bonus.getExtraFee(),
            bonus.getMinTotalFee(),
            bonus.getActive());
    }

    /** memasukkan ke dalam database psql
     * @param referral kode referral Bonus
     * @param extraFee extra fee Bonus
     * @param minTotalFee minimum total fee Bonus
     * @param active status aktif Bonus
     * @return nomor id dari Bonus yang tercatat dalam psql
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static int insertBonus(String referral, int extraFee, int minTotalFee, boolean active) throws Exception {   
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_INSERT);
        ps.setString(1, referral);
        ps.setInt(2, extraFee);
        ps.setInt(3, minTotalFee);
        ps.setBoolean(4, active);
        ResultSet rs = ps.executeQuery();
        return rs.next() ? rs.getInt(1) : -1;
    }

    /** menghapus Bonus dari database psql
     * @param id nomor id dari Bonus
     * @return true jika berhasil menghapus sesuai parameter
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static boolean removeBonus(int id) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_REMOVE);
        ps.setInt(1, id);
        return ps.executeUpdate() == 1;
    }

    /** meminta Bonus dari database psql
     * @param id nomor id dari Bonus
     * @return Bonus yang sesuai parameter, jika tidak ditemukan return null
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static Bonus getBonus(int id) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GET_ID);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return toBonus(rs);
        return null;
    }

    /** meminta seluruh Bonus dari database psql
     * @return Bonus yang tercatat atau diterima oleh API, jika kosong return empty list
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static ArrayList<Bonus> getAllBonus() throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GET_ALL);
        ResultSet rs = ps.executeQuery();
        ArrayList<Bonus> list = new ArrayList<>();
        while (rs.next())
            list.add(toBonus(rs));
        return list;
    }

    /** meminta Bonus dari database psql melalui kode referral
     * @param referral kode referal dari bonus
     * @return Bonus yang terikat dengan kode referral parameter
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static Bonus getBonusByReferral(String referral) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GET_REF);
        ps.setString(1, referral);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return toBonus(rs);
        return null;
    }

    /** meminta Bonus id terakhir dalam database
     * @return id Bonus, jika tidak ada return -1
     * @throws Exception error dalam koneksi ataupun saat mengurai data
     */
    public static int getLastBonusId() throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_LASTID);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return rs.getInt(1);
        return -1;
    }
    
    /** mengubah result set dari API menjadi objek Bonus. Tidak dengan otomatis
     * memperbarui baris, panggil rs.next() terlebih dahulu untuk menyesuaikan baris
     * @param rs hasil dari java.sql.PreparedStatement.executeQuery()
     * @return Bonus yang sesuai
     * @throws Exception error mengurai data ResultSet
     */
    private static Bonus toBonus(ResultSet rs) throws Exception {
        return new Bonus(
            rs.getInt("id"),
            rs.getString("referralcode").stripTrailing(),
            rs.getInt("extraFee"),
            rs.getInt("mintotalfee"),
            rs.getBoolean("active")
        );
    }
}
