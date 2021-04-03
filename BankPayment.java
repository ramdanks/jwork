/**
 * Kelas untuk menyimpan informasi faktur ewallet (cashless)
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 010421
 */

public class BankPayment extends Invoice 
{
    private static final PaymentType PAYMENT_TYPE = PaymentType.BankPayment;
    private int adminFee;
    
    public BankPayment(int id, Job job, String date, Jobseeker jobseeker,
                        InvoiceStatus invoiceStatus) {
        super(id, job, date, jobseeker, invoiceStatus);
        this.adminFee = 0;
    }
    public BankPayment(int id, Job job, String date, Jobseeker jobseeker,
                        InvoiceStatus invoiceStatus, int adminFee) {
        super(id, job, date, jobseeker, invoiceStatus);
        this.adminFee = adminFee;
    }
    public PaymentType getPaymentType() {
        return PAYMENT_TYPE;
    }
    public int getAdminFee() {
        return adminFee;
    }
    public void setAdminFee(int adminFee) {
        this.adminFee = adminFee;
    }
    public void setTotalFee() {
        totalFee = getJob().getFee();
        if (adminFee != 0) {
            totalFee -= adminFee;
        }
    }
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
