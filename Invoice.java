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
    private PaymentType paymentType;
    private InvoiceStatus status;
    
    public Invoice(int id, int idJob, String date, int totalFee,
                    Jobseeker jobseeker, PaymentType paymentType,
                    InvoiceStatus status) {
        this.id = id;
        this.idJob = idJob;
        this.date = date;
        this.totalFee = totalFee;
        this.jobseeker = jobseeker;
        this.paymentType = paymentType;
        this.status = status;
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
     * akses tipe pembayaran
     * @return PaymentType: paymentType
     */
    public PaymentType getPaymentType() {
        return paymentType;
    }
    /**
     * akses status faktur / invoice
     * @return InvoiceStatus: status
     */
    public InvoiceStatus getInvoiceStatus() {
        return status;
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
     * mutasi tipe pembayaran
     * @param PaymentType: menset paymentType
     */
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
    /**
     * mutasi status faktur / invoice
     * @param InvoiceStatus: menset status
     */
    public void setInvoiceStatus(InvoiceStatus status) {
        this.status = status;
    }
    /**
     * mencetak seluruh informasi faktur ke terminal
     */
    public void printData() {
        // Melakukan pencetakan kepada console (terminal)
        // tentang setiap data yang tercatat pada faktur
        System.out.println("====== Invoice ======");
        System.out.println("ID     : " + this.id);
        System.out.println("ID Job : " + this.idJob);
        System.out.println("Date   : " + this.date);
        System.out.println("Seeker : " + this.jobseeker.getName());
        System.out.println("Fee    : " + this.totalFee);
        System.out.println("Status : " + this.status.toString());
    }
}
