package RamadhanKalih.jwork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

public class DatabaseJobseekerPostgre {

    private static final String Q_INSERT    = "INSERT INTO jobseeker(name,email,password) VALUES (?,?,?)";
    private static final String Q_REMOVE    = "DELETE FROM jobseeker WHERE id=?";
    private static final String Q_GET1      = "SELECT * FROM jobseeker WHERE id=?";
    private static final String Q_GET2      = "SELECT * FROM jobseeker WHERE email=? AND password=?";
    private static final String Q_GETLASTID = "SELECT id FROM jobseeker ORDER BY id DESC LIMIT 1";

    public static boolean insertJobseeker(Jobseeker jobseeker) throws Exception {
        return insertJobseeker(
            jobseeker.getName(),
            jobseeker.getEmail(),
            jobseeker.getPassword());
    }

    public static boolean insertJobseeker(String name, String email, String password) throws Exception {   
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_INSERT);
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, password);
        return ps.executeUpdate() == 1;
    }

    public static boolean removeJobseeker(int jobseekerId) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_REMOVE);
        ps.setInt(1, jobseekerId);
        return ps.executeUpdate() == 1;
    }

    public static Jobseeker getJobseeker(int jobseekerId) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GET1);
        ps.setInt(1, jobseekerId);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return toJobseeker(rs);
        return null;
    }

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

    public static int getLastJobseekerId() throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GETLASTID);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return rs.getInt(1);
        return -1;
    }
    
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
