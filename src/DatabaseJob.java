import java.util.ArrayList;

/**
 * Kelas yang menyimpan list pekerjaan yang tersedia
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210325
 */

public class DatabaseJob
{
    /** menyimpan list Job dari namanya */
    private static ArrayList<Job> JOB_DATABASE = new ArrayList<Job>();
    private static int lastId = 0;

    /**
     * meminta seluruh Job yang tercatat dalam database
     * @return ArrayList<Job>
     */
    public static ArrayList<Job> getJobDatabase() {
        return JOB_DATABASE;
    }
    public static int getLastId() {
        return lastId;
    }
    /**
     * meminta Job berdasarkan id
     * @param int id milik job
     * @return Job: Job
     */
    public static Job getJobById(int id) {
        for (Job j : JOB_DATABASE)
            if (j.getId() == id)
                return j;
        return null;
    }
    public static ArrayList<Job> getJobByRecruiter(int recruiterId) {
        ArrayList<Job> jobs = new ArrayList<Job>();
        for (Job j : JOB_DATABASE)
            if (j.getRecruiter().getId() == recruiterId)
                jobs.add(j);
        if (jobs.isEmpty())
            return null;
        return jobs;
    }
    public static ArrayList<Job> getJobByCategory(JobCategory category) {
        ArrayList<Job> jobs = new ArrayList<Job>();
        for (Job j : JOB_DATABASE)
            if (j.getCategory() == category)
                jobs.add(j);
        if (jobs.isEmpty())
            return null;
        return jobs;
    }
    /**
     * menambah entri Job kedalam database
     * @param Job: Job
     * @return boolean:
     */
    public static boolean addJob(Job job) {
        for (Job j : JOB_DATABASE)
            if (j.getId() == job.getId())
                return false;
        JOB_DATABASE.add(job);
        lastId = job.getId();
        return true;
    }
    /**
     * menghapus entri Job kedalam database
     * @param Job: Job
     * @return boolean:
     */
    public static boolean removeJob(int id) {
        for (int i = 0; i < JOB_DATABASE.size(); i++)
        {
            if (JOB_DATABASE.get(i).getId() == id)
            {
                JOB_DATABASE.remove(i);
                return true;
            }
        }
        return false;
    }
}
