/**
 * Kelas yang menyimpan list pekerjaan yang tersedia
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210325
 */

public class DatabaseJob
{
    private static String[] listJob;
    
    public DatabaseJob() {}
    /**
     * menambah entri job kedalam database
     * @param Job: job
     * @return boolean:
     */
    public static boolean addJob(Job job) {
        System.out.println("Job ditambahkan ke Database");
        return false;
    }
    /**
     * menghapus entri job kedalam database
     * @param Job: job
     * @return boolean:
     */
    public static boolean removeJob(Job job) {
        return false;
    }
    /**
     * meminta job dari database
     * @return Job: job
     */
    public static Job getJob() {
        return null;
    }
    /**
     * meminta seluruh list yang tercatat dalam database
     * @return String[]: list job
     */
    public static String[] getListJob() {
        return listJob;
    }
}
