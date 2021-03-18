/**
* @Author: Ramadhan Kalih Sewu (1806148826)
* @Version: 210318
* 
* Kelas untuk menyimpan list pekerjaan yang tersedia
*/

public class DatabaseJob
{
    private String[] listJob;
    
    public DatabaseJob() {}
    /**
     * menambah entri job kedalam database
     * @param Job: job
     * @return boolean:
     */
    public boolean addJob(Job job) {
        return false;
    }
    /**
     * menghapus entri job kedalam database
     * @param Job: job
     * @return boolean:
     */
    public boolean removeJob(Job job) {
        return false;
    }
    /**
     * meminta job dari database
     * @return Job: job
     */
    public Job getJob() {
        return null;
    }
    /**
     * meminta seluruh list yang tercatat dalam database
     * @return String[]: list job
     */
    public String[] getListJob() {
        return null;
    }
}
