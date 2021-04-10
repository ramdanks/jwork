import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Kelas menyimpan informasi faktur pembayaran bank
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 030421
 * @inheritDoc
 */

public class BankPayment extends Invoice 
{
    /** tipe pembayaran sesuai enum @PaymentType */
    private static final PaymentType PAYMENT_TYPE = PaymentType.BankPayment;
    /** tarif admin */
    private int adminFee;
    
    /** ctor tanpa tarif admin */
    public BankPayment(int id, Job job, Jobseeker jobseeker,
                        InvoiceStatus invoiceStatus) {
        super(id, job, jobseeker, invoiceStatus);
        this.adminFee = 0;
    }
    /** ctor dengan tarif admin */
    public BankPayment(int id, Job job, Jobseeker jobseeker,
                        InvoiceStatus invoiceStatus, int adminFee) {
        super(id, job, jobseeker, invoiceStatus);
        this.adminFee = adminFee;
    }
    /**
     * mengakses tipe pembayaran @Invoice sesuai enum @PaymentType
     * @return PaymentType
     */
    public PaymentType getPaymentType() {
        return PAYMENT_TYPE;
    }
    /**
     * mengakses tarif administrasi
     * @return int
     */
    public int getAdminFee() {
        return adminFee;
    }
    /**
     * mutasi tarif administrasi
     * @param int
     */
    public void setAdminFee(int adminFee) {
        this.adminFee = adminFee;
    }
    /** memperbarui total fee berdasarkan bonus */
    public void setTotalFee() {
        totalFee = getJob().getFee();
        if (adminFee != 0) {
            totalFee -= adminFee;
        }
    }
    /** mencetak informasi pembayaran bank ke terminal */
    public String toString() {
        Date date = getDate().getTime();
        String strDate = "";
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            strDate = dateFormat.format(date);
        }
         // Print data secara keseluruhan
         return     "====== Bank Payment ======" +
                    "\nID           : " + getId() +
                    "\nJob          : " + getJob().getName() +
                    "\nDate         : " + strDate +
                    "\nSeeker       : " + getJobseeker().getName() +
                    "\nAdmin Fee    : " + adminFee +
                    "\nFee          : " + totalFee +
                    "\nStatus       : " + getInvoiceStatus().toString() +
                    "\nPayment Type : " + PAYMENT_TYPE.toString();
    }
}
