package RamadhanKalih.jwork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLType;
import java.sql.Date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DatabaseInvoicePostgre {
    private static final String Q_INSERT        = "INSERT INTO invoice(job_id,date,totalfee,jobseeker_id,status,bonus_id,adminfee,ptype) VALUES (?,?,?,?,?,?,?,?)";
    private static final String Q_REMOVE        = "DELETE FROM invoice WHERE id=?";
    private static final String Q_GET_ALL       = "SELECT * FROM invoice";
    private static final String Q_GET_ID        = "SELECT * FROM invoice WHERE id=?";
    private static final String Q_GET_JS        = "SELECT * FROM invoice WHERE jobseeker_id=?";
    private static final String Q_ALTER_STATUS  = "UPDATE invoice SET status=? WHERE id=?";
    private static final String Q_LASTID        = "SELECT id FROM invoice ORDER BY id DESC LIMIT 1";
    private static final String Q_EXIST_STATUS  = "SELECT EXISTS(SELECT 1 FROM invoice WHERE id=? AND status=?)";

    public static boolean insertInvoice(Invoice invoice) throws Exception {
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

    public static boolean insertInvoice(int jobId, Calendar date, int totalFee,
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
        return ps.executeUpdate() == 1;
    }

    public static boolean removeInvoice(int id) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_REMOVE);
        ps.setInt(1, id);
        return ps.executeUpdate() == 1;
    }

    public static Invoice getInvoice(int id) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GET_ID);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return toInvoice(rs);
        return null;
    }

    public static ArrayList<Invoice> getAllInvoice() throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GET_ALL);
        ResultSet rs = ps.executeQuery();   
        ArrayList<Invoice> list = new ArrayList<>();
        while (rs.next())
            list.add(toInvoice(rs));
        return list;
    }

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

    public static int getLastJobId() throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_LASTID);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return rs.getInt(1);
        return -1;
    }

    public static boolean changeInvoiceStatus(int id, InvoiceStatus status) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_ALTER_STATUS);
        ps.setString(1, status.toString());
        ps.setInt(2, id);
        return ps.executeUpdate() == 1;
    }

    public static boolean isExist(int id, InvoiceStatus status) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_EXIST_STATUS);
        ps.setInt(1, id);
        ps.setString(2, status.toString());
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return rs.getBoolean(1);
        return false;
    }
    
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
