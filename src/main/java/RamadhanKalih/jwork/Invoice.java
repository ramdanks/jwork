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
    /** nomor unik dari invoice */
    private int id;
    /** job dalam invoice */
    private ArrayList<Job> jobs;
    /** tanggal invoice dibangun */
    private Calendar date;
    /** akumulasi fee  */
    protected int totalFee;
    /** jobseeker yang memiliki invoice */
    private Jobseeker jobseeker;
    /** status dari invoice */
    private InvoiceStatus invoiceStatus;

    /** adminfee diproses oleh BankPayment */
    protected int adminFee = 0;
    /** bonus diproses oleh EwalletPayment */
    protected Bonus bonus = null;
    /** menyimpan jenis pembayaran */
    protected PaymentType type;
    
    /** ctor untuk inisialisasi variable */
    public Invoice(int id, ArrayList<Job> jobs, Jobseeker jobseeker) {
        this.id = id;
        this.jobs = jobs;
        this.jobseeker = jobseeker;
        this.invoiceStatus = InvoiceStatus.OnGoing;
        date = Calendar.getInstance();
    }
    
    /** akses id faktur
     * @return nomor id faktur
     */
    public int getId() {
        return id;
    }

    /** akses job pada faktur
     * @return job pada faktur
     */
    public ArrayList<Job> getJobs() {
        return jobs;
    }

    /** akses tanggal faktur
     * @return tanggal faktur
     */
    public Calendar getDate() {
        return date;
    }

    /** akses total tagihan faktur
     * @return jumlah tagihan
     */
    public int getTotalFee() {
        return totalFee;
    }

    /** akses jobseeker yang memiliki faktur ini
     * @return jobseeker
     */
    public Jobseeker getJobseeker() {
        return jobseeker;
    }

    /** akses tipe pembayaran
     * @return tipe pembayaran dari subclass @Invoice
     */
    public abstract PaymentType getPaymentType();

    /** akses status faktur / invoice
     * @return status invoice ini
     */
    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    /** mutasi id faktur
     * @param id nomor id yang baru
     */
    public void setId(int id) {
        this.id = id;
    }

    /** mutasi job faktur
     * @param jobs job yang baru
     */
    public void setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
    }

    /** mutasi tanggal faktur
     * @param date tanggal yang baru
     */
    public void setDate(Calendar date) {
        this.date = date;
    }

    /** mutasi tanggal faktur
     * @param year tahun dalam bilangan bulat
     * @param month bulan dalam hitungan tahun 1-12
     * @param dayOfMonth hari dalam hitungan bulan 1-31 / 1-30 / 1-29 / 1-28
     */
    public void setDate(int year, int month, int dayOfMonth) {
        date = new GregorianCalendar(year, month-1, dayOfMonth);
    }

    /** memperbarui nilai total fee sesuai dengan aturan subclass */
    public abstract void setTotalFee();

    /** mutasi jobseeker
     * @param jobseeker jobseeker yang baru
     */
    public void setJobseeker(Jobseeker jobseeker) {
        this.jobseeker = jobseeker;
    }

    /** mutasi status faktur / invoice
     * @param invoiceStatus status yang baru
     */
    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }
    /** mencetak seluruh informasi faktur ke terminal */
    public abstract String toString();
}
