/**
 * Kelas untuk menyimpan informasi tentang entri kerja
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210325
 */

public class Job
{
    private int id;
    private String name;
    private Recruiter recruiter;
    private int fee;
    private JobCategory category;
    
    /** ctor untuk inisialisasi variable */
    public Job(int id, String name, Recruiter recruiter, int fee,
                JobCategory category) {
        this.id = id;
        this.name = name;
        this.recruiter = recruiter;
        this.fee = fee;
        this.category = category;
    }
    /**
     * akses nomor id job
     * @return int: id job
     */
    public int getId(){
        return id;
    }
    /**
     * akses nama job
     * @return String: nama job
     */
    public String getName() {
        return name;
    }
    /**
     * akses bayaran/upah/gaji job
     * @return int: gaji job
     */
    public int getFee() {
        return fee;
    }
    /**
     * akses kategori job
     * @return String: kategori job
     */
    public JobCategory getCategory() {
        return category;
    }
    /**
     * akses recruiter
     * @return Recruiter: recruiter
     */
    public Recruiter getRecruiter() {
        return recruiter;
    }
    /**
     * mutasi id job
     * @param int: id job
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * mutasi nama job
     * @param String: menset nama job
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * mutasi recruiter job
     * @param Recruiter: recruiter job
     */
    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }
    /**
     * mutasi bayaran/upah/gaji job
     * @param int: gaji job
     */
    public void setFee(int fee) {
        this.fee = fee;
    }
    /**
     * mutasi kategori job
     * @param String: kategori job
     */
    public void setCategory(JobCategory category) {
        this.category = category;
    }
    public String toString() {
        return  "============ JOB ============" +
                "\nId = " + id +
                "\nName = " + name +
                "\nRecruiter = " + recruiter.getName() +
                "\nCity = " + recruiter.getLocation().getCity() +
                "\nFee = " + fee +
                "\nCategory = " + category.toString();
    }
}
