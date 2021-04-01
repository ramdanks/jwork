/**
 * Kelas untuk menyimpan informasi faktur
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 010421
 */

public abstract class Invoice
{
    private int id;
    private Job job;
    private String date;
    protected int totalFee;
    private Jobseeker jobseeker;
    private InvoiceStatus invoiceStatus;
    
    public Invoice(int id, Job job, String date,
                    Jobseeker jobseeker, InvoiceStatus invoiceStatus) {
        this.id = id;
        this.job = job;
        this.date = date;
        this.jobseeker = jobseeker;
        this.invoiceStatus = invoiceStatus;
    }
    /**
     * akses id faktur
     * @return int: nomor id faktur
     */
    public int getId() {
        return id;
    }
    /**
     * akses job pada faktur
     * @return Job: class Job
     */
    public Job getJob() {
        return job;
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
    public abstract PaymentType getPaymentType();
    /**
     * akses status faktur / invoice
     * @return InvoiceStatus: status
     */
    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }
    /**
     * mutasi id faktur
     * @param int: menset id faktur
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * mutasi job faktur
     * @param Job: menset job pada faktur
     */
    public void setJob(Job job) {
        this.job = job;
    }
    /**
     * mutasi tanggal faktur
     * @param String: menset tanggal faktur
     */
    public void setDate(String date) {
        this.date = date;
    }
    /**
     * mutasi atau perbarui jumlah bayaran
     */
    public abstract void setTotalFee();
    /**
     * mutasi jobseeker
     * @param Jobseeker: menset jobseeker
     */
    public void setJobseeker(Jobseeker jobseeker) {
        this.jobseeker = jobseeker;
    }
    /**
     * mutasi status faktur / invoice
     * @param InvoiceStatus: menset status
     */
    public void setInvoiceStatus(InvoiceStatus status) {
        this.invoiceStatus = status;
    }
    /**
     * mencetak seluruh informasi faktur ke terminal
     */
    public abstract void printData();
}
