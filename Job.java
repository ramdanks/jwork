/**
* @Author: Ramadhan Kalih Sewu (1806148826)
* @Version: 210319
* 
* Kelas untuk menyimpan informasi tentang entri kerja
*/

public class Job
{
    private int id;
    private String name;
    private Recruiter recruiter;
    private int fee;
    private String category;
    
    public Job(int id, String name, Recruiter recruiter, int fee,
                String category) {
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
        return this.id;
    }
    /**
     * akses nama job
     * @return String: nama job
     */
    public String getName() {
        return this.name;
    }
    /**
     * akses bayaran/upah/gaji job
     * @return int: gaji job
     */
    public int getFee() {
        return this.fee;
    }
    /**
     * akses kategori job
     * @return String: kategori job
     */
    public String getCategory() {
        return this.category;
    }
    /**
     * akses recruiter
     * @return Recruiter: recruiter
     */
    public Recruiter getRecruiter() {
        return this.recruiter;
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
    public void setCategory(String category) {
        this.category = category;
    }
    /**
     * mencetak informasi job ke terminal
     */
    public void printData() {
        System.out.println("[Job]");
        System.out.println("id       : " + this.id);
        System.out.println("name     : " + this.name);
        System.out.println("recruiter: " + this.recruiter.getName());
        System.out.println("fee      : " + this.fee);
        System.out.println("category : " + this.category);
    }
}
