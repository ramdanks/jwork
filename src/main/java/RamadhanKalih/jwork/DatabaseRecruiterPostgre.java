package RamadhanKalih.jwork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

public class DatabaseRecruiterPostgre {

    private static final String Q_INSERT    = "INSERT INTO recruiter(name,email,phonenumber,loc_province,loc_city,loc_desc) VALUES (?,?,?,?,?,?)";
    private static final String Q_REMOVE    = "DELETE FROM recruiter WHERE id=?";
    private static final String Q_GET       = "SELECT * FROM recruiter WHERE id=?";
    private static final String Q_GETALL    = "SELECT * FROM recruiter";
    private static final String Q_GETLASTID = "SELECT id FROM recruiter ORDER BY id DESC LIMIT 1";

    public static boolean insertRecruiter(Recruiter recruiter) throws Exception {   
        return insertRecruiter(
            recruiter.getName(),
            recruiter.getEmail(),
            recruiter.getPhoneNumber(),
            recruiter.getLocation().getProvince(),
            recruiter.getLocation().getCity(),
            recruiter.getLocation().getDescription());
    }

    public static boolean insertRecruiter(  String name, String email, String phoneNumber,
                                            String loc_province, String loc_city, String loc_desc) throws Exception {   
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_INSERT);
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, phoneNumber);
        ps.setString(4, loc_province);
        ps.setString(5, loc_city);
        ps.setString(6, loc_desc);
        return ps.executeUpdate() == 1;
    }

    public static boolean removeRecruiter(int recruiterId) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_REMOVE);
        ps.setInt(1, recruiterId);
        return ps.executeUpdate() == 1;
    }

    public static Recruiter getRecruiter(int recruiterId) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GET);
        ps.setInt(1, recruiterId);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return toRecruiter(rs);
        return null;
    }

    public static ArrayList<Recruiter> getAllRecruiter() throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GETALL);
        ResultSet rs = ps.executeQuery();
        ArrayList<Recruiter> list = new ArrayList<>();
        while (rs.next())
            list.add(toRecruiter(rs));
        return list;
    }

    public static int getLastRecruiterId() throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GETLASTID);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return rs.getInt(1);
        return -1;
    }
    
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
            rs.getString("phonenumber").stripTrailing(),
            loc
        );
    }
}
