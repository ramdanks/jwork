package RamadhanKalih.jwork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

public class DatabaseBonusPostgre 
{
    private static final String Q_INSERT    = "INSERT INTO bonus(referralcode,extrafee,mintotalfee,active) VALUES (?,?,?,?) RETURNING id";
    private static final String Q_REMOVE    = "DELETE FROM bonus WHERE id=?";
    private static final String Q_GET_ALL   = "SELECT * FROM bonus";
    private static final String Q_GET_ID    = "SELECT * FROM bonus WHERE id=?";
    private static final String Q_GET_REF   = "SELECT * FROM bonus WHERE referralcode=?";
    private static final String Q_LASTID    = "SELECT id FROM bonus ORDER BY id DESC LIMIT 1";

    public static int insertBonus(Bonus bonus) throws Exception {
        return insertBonus(
            bonus.getReferralCode(),
            bonus.getExtraFee(),
            bonus.getMinTotalFee(),
            bonus.getActive());
    }

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

    public static boolean removeBonus(int bonusId) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_REMOVE);
        ps.setInt(1, bonusId);
        return ps.executeUpdate() == 1;
    }

    public static Bonus getBonus(int bonusId) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GET_ID);
        ps.setInt(1, bonusId);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return toBonus(rs);
        return null;
    }

    public static ArrayList<Bonus> getAllBonus() throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GET_ALL);
        ResultSet rs = ps.executeQuery();
        ArrayList<Bonus> list = new ArrayList<>();
        while (rs.next())
            list.add(toBonus(rs));
        return list;
    }

    public static Bonus getBonusByReferral(String referral) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GET_REF);
        ps.setString(1, referral);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return toBonus(rs);
        return null;
    }

    public static int getLastBonusId() throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_LASTID);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return rs.getInt(1);
        return -1;
    }
    
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
