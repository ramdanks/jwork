package RamadhanKalih.jwork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

public class DatabaseJobPostgre {

    private static final String Q_INSERT    = "INSERT INTO job(name,recruiter_id,fee,category) VALUES (?,?,?,?)";
    private static final String Q_REMOVE    = "DELETE FROM job WHERE id=?";
    private static final String Q_GET_ALL   = "SELECT * FROM job";
    private static final String Q_GET_ID    = "SELECT * FROM job WHERE id=?";
    private static final String Q_GET_REC   = "SELECT * FROM job WHERE recruiter_id=?";
    private static final String Q_GET_CAT   = "SELECT * FROM job WHERE category=?";
    private static final String Q_LASTID    = "SELECT id FROM job ORDER BY id DESC LIMIT 1";

    public static boolean insertJob(Job job) throws Exception {   
        return insertJob(
            job.getName(),
            job.getRecruiter().getId(),
            job.getFee(),
            job.getCategory());
    }

    public static boolean insertJob(String name, int recruiterId, int fee, JobCategory category) throws Exception {   
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_INSERT);
        ps.setString(1, name);
        ps.setInt(2, recruiterId);
        ps.setInt(3, fee);
        ps.setString(4, category.toString());
        return ps.executeUpdate() == 1;
    }

    public static boolean removeJob(int jobId) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_REMOVE);
        ps.setInt(1, jobId);
        return ps.executeUpdate() == 1;
    }

    public static Job getJob(int jobId) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GET_ID);
        ps.setInt(1, jobId);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return toJob(rs);
        return null;
    }

    public static ArrayList<Job> getAllJob() throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GET_ALL);
        ResultSet rs = ps.executeQuery();
        ArrayList<Job> list = new ArrayList<>();
        while (rs.next())
            list.add(toJob(rs));
        return list;
    }

    public static ArrayList<Job> getJobByRecruiter(int jobseekerId) throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_GET_REC);
        ps.setInt(1, jobseekerId);
        ResultSet rs = ps.executeQuery();
        ArrayList<Job> list = new ArrayList<>();
        while (rs.next())
            list.add(toJob(rs));
        return list;
    }

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

    public static int getLastJobId() throws Exception {
        Connection c = DatabaseConnectionPostgre.connection();
        PreparedStatement ps = c.prepareStatement(Q_LASTID);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return rs.getInt(1);
        return -1;
    }
    
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
