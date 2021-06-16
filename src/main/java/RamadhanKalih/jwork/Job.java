package RamadhanKalih.jwork;

/** Kelas untuk menyimpan informasi tentang entri kerja
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210325
 */
public class Job
{
    /** nomor unik dari job */
    private int id;
    /** nama job */
    private String name;
    /** recruiter dari job ini */
    private Recruiter recruiter;
    /** upah / bayaran / gaji */
    private int fee;
    /** kategori job */
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
    
    /** akses nomor id job
     * @return nomor id job
     */
    public int getId(){
        return id;
    }

    /** akses nama job
     * @return nama job
     */
    public String getName() {
        return name;
    }

    /** akses bayaran/upah/gaji job
     * @return fee job
     */
    public int getFee() {
        return fee;
    }

    /** akses kategori job
     * @return kategori job
     */
    public JobCategory getCategory() {
        return category;
    }

    /** akses recruiter
     * @return recruiter dari job
     */
    public Recruiter getRecruiter() {
        return recruiter;
    }

    /** mutasi id job
     * @param id nomor id yang baru
     */
    public void setId(int id) {
        this.id = id;
    }

    /** mutasi nama job
     * @param name nama yang baru
     */
    public void setName(String name) {
        this.name = name;
    }

    /** mutasi recruiter job
     * @param recruiter recruiter yang baru
     */
    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }

    /** mutasi bayaran/upah/gaji job
     * @param fee fee yang baru
     */
    public void setFee(int fee) {
        this.fee = fee;
    }

    /** mutasi kategori job
     * @param category kategori yang baru
     */
    public void setCategory(JobCategory category) {
        this.category = category;
    }

    /** mengakses atribut yang ada dalam kelas
     * @return String seluruh informasi pada kelas
     */
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
