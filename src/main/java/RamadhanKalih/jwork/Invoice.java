package RamadhanKalih.jwork;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/** Kelas untuk menyimpan informasi faktur
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210424
 */
public abstract class Invoice
{
    private int id;
    private ArrayList<Job> jobs;
    private Calendar date;
    protected int totalFee;
    private Jobseeker jobseeker;
    private InvoiceStatus invoiceStatus;
    
    /** ctor untuk inisialisasi variable */
    public Invoice(int id, ArrayList<Job> jobs, Jobseeker jobseeker) {
        this.id = id;
        this.jobs = jobs;
        this.jobseeker = jobseeker;
        this.invoiceStatus = InvoiceStatus.OnGoing;
        date = Calendar.getInstance();
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
    public ArrayList<Job> getJobs() {
        return jobs;
    }
    /**
     * akses tanggal faktur
     * @return String: tanggal faktur
     */
    public Calendar getDate() {
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
    public void setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
    }
    /**
     * mutasi tanggal faktur
     * @param String: menset tanggal faktur
     */
    public void setDate(Calendar date) {
        this.date = date;
    }
    public void setDate(int year, int month, int dayOfMonth) {
        date = new GregorianCalendar(year, month-1, dayOfMonth);
    }
    /** memperbarui nilai total fee */
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
    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }
    /** mencetak seluruh informasi faktur ke terminal */
    public abstract String toString();
}
