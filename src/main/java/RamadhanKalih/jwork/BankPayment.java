package RamadhanKalih.jwork;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/** Kelas menyimpan informasi faktur pembayaran bank
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210517
 * @inheritDoc
 */
public class BankPayment extends Invoice 
{
    /** tipe pembayaran sesuai enum @PaymentType */
    private static final PaymentType PAYMENT_TYPE = PaymentType.BankPayment;
    
    /** ctor tanpa tarif admin */
    public BankPayment(int id, ArrayList<Job> jobs, Jobseeker jobseeker) {
        super(id, jobs, jobseeker);
        super.adminFee = 0;
        super.type = PAYMENT_TYPE;
    }
    
    /** ctor dengan tarif admin */
    public BankPayment(int id, ArrayList<Job> jobs, Jobseeker jobseeker, int adminFee) {
        super(id, jobs, jobseeker);
        super.adminFee = adminFee;
        super.type = PAYMENT_TYPE;
    }

    /** mengakses tipe pembayaran @Invoice sesuai enum @PaymentType
     * @return PaymentType
     */
    public PaymentType getPaymentType() {
        return PAYMENT_TYPE;
    }

    /** mengakses tarif administrasi
     * @return int
     */
    public int getAdminFee() {
        return adminFee;
    }

    /** mutasi tarif administrasi
     * @param adminFee
     */
    public void setAdminFee(int adminFee) {
        this.adminFee = adminFee;
    }

    /** memperbarui total fee berdasarkan bonus */
    public void setTotalFee() {
        // perbarui total fee dengan fee jobs yang ada
        this.totalFee = 0;
        for (Job j : getJobs())
            this.totalFee += j.getFee();
        // tambahkan total fee jika ada admin fee
        if (getAdminFee() != 0)
            this.totalFee += getAdminFee();
    }

    /** mengembalikan seluruh informasi yang disimpan dalam kelas */
    public String toString() {
        Date date = getDate().getTime();
        String strDate = "";
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            strDate = dateFormat.format(date);
        }
         // Print data secara keseluruhan
         return     "====== Bank Payment ======" +
                    "\nID           : " + getId() +
                    "\nJobs         : " + getJobs().size() +
                    "\nDate         : " + strDate +
                    "\nSeeker       : " + getJobseeker().getName() +
                    "\nAdmin Fee    : " + adminFee +
                    "\nFee          : " + totalFee +
                    "\nStatus       : " + getInvoiceStatus().toString() +
                    "\nPayment Type : " + PAYMENT_TYPE.toString();
    }
}
