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
    public BankPayment(int id, Job job, String date, Jobseeker jobseeker,
                        InvoiceStatus invoiceStatus) {
        super(id, job, date, jobseeker, invoiceStatus);
        this.adminFee = 0;
    }
    /** ctor dengan tarif admin */
    public BankPayment(int id, Job job, String date, Jobseeker jobseeker,
                        InvoiceStatus invoiceStatus, int adminFee) {
        super(id, job, date, jobseeker, invoiceStatus);
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
    public void printData() {
         // Print data secara keseluruhan
         System.out.println("====== Bank Payment ======");
         System.out.println("ID           : " + getId());
         System.out.println("Job          : " + getJob().getName());
         System.out.println("Date         : " + getDate());
         System.out.println("Seeker       : " + getJobseeker().getName());
         System.out.println("Admin Fee    : " + adminFee);
         System.out.println("Fee          : " + totalFee);
         System.out.println("Status       : " + getInvoiceStatus().toString());
         System.out.println("Payment Type : " + PAYMENT_TYPE.toString());
    }
}
