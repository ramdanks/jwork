package RamadhanKalih.jwork;
import java.util.ArrayList;

/** Kelas yang menyimpan list pekerjaan yang tersedia
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210325
 */
public class DatabaseJob
{
    /** menyimpan list Job */
    private static ArrayList<Job> JOB_DATABASE = new ArrayList<Job>();
    /** menyimpan nomor id Bonus yang terakhir berhasil masuk ke database melalui method addJob() */
    private static int lastId = 0;

    /** meminta seluruh Job yang tercatat dalam database
     * @return ArrayList<Job>
     */
    public static ArrayList<Job> getJobDatabase() {
        return JOB_DATABASE;
    }

    /** meminta nomor id Bonus yang terakhir masuk ke database melalui method addJob()
     * @return nomor id Job
     */
    public static int getLastId() {
        return lastId;
    }

    /** meminta Job berdasarkan id
     * @param id nomor id Job yang diminta
     * @return Job yang memiliki id sesuai parameter
     * @throws JobNotFoundException apabila tidak ditemukan Job sesuai parameter
     */
    public static Job getJobById(int id) throws JobNotFoundException {
        for (Job j : JOB_DATABASE)
            if (j.getId() == id)
                return j;
        throw new JobNotFoundException(id);
    }

    /** meminta job dalam database kelas ini
     * @param recruiterId nomor id recruiter
     * @return ArrayList<Job> list job yang terikat dengan parameter
     * @return null jika tidak ditemukan job yang terikat dengan parameter
     */
    public static ArrayList<Job> getJobByRecruiter(int recruiterId) {
        ArrayList<Job> jobs = new ArrayList<Job>();
        for (Job j : JOB_DATABASE)
            if (j.getRecruiter().getId() == recruiterId)
                jobs.add(j);
        if (jobs.isEmpty())
            return null;
        return jobs;
    }

    /** meminta job dalam database kelas ini
     * @param category kategori job yang diminta
     * @return list job yang terikat dengan parameter, 
     * jika tidak ditemukan maka return null
     */
    public static ArrayList<Job> getJobByCategory(JobCategory category) {
        ArrayList<Job> jobs = new ArrayList<Job>();
        for (Job j : JOB_DATABASE)
            if (j.getCategory() == category)
                jobs.add(j);
        if (jobs.isEmpty())
            return null;
        return jobs;
    }

    /** mencatat Job kedalam database
     * @param job Job yang ingin dimasukkan
     * @return true jika berhasil
     * @return false jika nomor id Job yang sama sudah ada dalam database
     */
    public static boolean addJob(Job job) {
        for (Job j : JOB_DATABASE)
            if (j.getId() == job.getId())
                return false;
        JOB_DATABASE.add(job);
        lastId = job.getId();
        return true;
    }

    /** menghapus entri Job dari database
     * @param id nomor id Job yang ingin dihapus
     * @return true jika berhasil menghapus
     * @throws JobNotFoundException apabila tidak ditemukan Job sesuai permintaan parameter
     */
    public static boolean removeJob(int id) throws JobNotFoundException {
        for (int i = 0; i < JOB_DATABASE.size(); i++)
        {
            if (JOB_DATABASE.get(i).getId() == id)
            {
                JOB_DATABASE.remove(i);
                return true;
            }
        }
        throw new JobNotFoundException(id);
    }
}
