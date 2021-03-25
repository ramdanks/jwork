/**
 * Kelas untuk menyimpan informasi faktur
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210325
 */

public class Invoice
{
    private int id;
    private int idJob;
    private String date;
    private int totalFee;
    private Jobseeker jobseeker;
    
    public Invoice(int id, int idJob, String date, int totalFee,
                    Jobseeker jobseeker) {
        this.id = id;
        this.idJob = idJob;
        this.date = date;
        this.totalFee = totalFee;
        this.jobseeker = jobseeker;
    }
    /**
     * akses id faktur
     * @return int: nomor id faktur
     */
    public int getId() {
        return id;
    }
    /**
     * akses id job faktur
     * @return int: nomor id pekerjaan dalam faktur
     */
    public int getIdJob() {
        return idJob;
    }
    /**
     * akses tanggal faktur
     * @return String: tanggal faktur
     */
    public String getDate() {
        return date;
    }
    /**
     * akses total tagihan faktur
     * @return int: jumlah tagihan
     */
    public int getTotalFee() {
        return totalFee;
    }
    /**
     * akses jobseeker
     * @return Jobseeker: jobseeker
     */
    public Jobseeker getJobseeker() {
        return jobseeker;
    }
    /**
     * mutasi id faktur
     * @param int: menset id faktur
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * mutasi id job faktur
     * @param int: menset id job faktur
     */
    public void setIdJobs(int idJob) {
        this.idJob = idJob;
    }
    /**
     * mutasi tanggal faktur
     * @param String: menset tanggal faktur
     */
    public void setDate(String date) {
        this.date = date;
    }
    /**
     * mutasi total tagihan faktur
     * @param int: menset total tagihan
     */
    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }
    /**
     * mutasi jobseeker
     * @param Jobseeker: menset jobseeker
     */
    public void setJobseeker(Jobseeker jobseeker) {
        this.jobseeker = jobseeker;
    }
    /**
     * mencetak seluruh informasi faktur ke terminal
     */
    public void printData() {
        // Melakukan pencetakan kepada console (terminal)
        // tentang setiap data yang tercatat pada faktur
        System.out.println("[Invoice]");
        System.out.println("id       : " + this.id);
        System.out.println("idJob    : " + this.idJob);
        System.out.println("date     : " + this.date);
        System.out.println("totalFee : " + this.totalFee);
        System.out.println("jobseeker: " + this.jobseeker.getName());
    }
}
