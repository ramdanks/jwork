/**
 * Kelas yang menyimpan list pekerjaan yang tersedia
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210325
 */

public class DatabaseJob
{
    private static String[] listJob;
    
    /**
     * menambah entri Job kedalam database
     * @param Job: Job
     * @return boolean:
     */
    public static boolean addJob(Job job) {
        System.out.println("Job ditambahkan ke Database");
        return false;
    }
    /**
     * menghapus entri Job kedalam database
     * @param Job: Job
     * @return boolean:
     */
    public static boolean removeJob(Job job) {
        return false;
    }
    /**
     * meminta Job dari database
     * @return Job: Job
     */
    public static Job getJob() {
        return null;
    }
    /**
     * meminta seluruh Job yang tercatat dalam database
     * @return String[]: list job
     */
    public static String[] getListJob() {
        return listJob;
    }
}
