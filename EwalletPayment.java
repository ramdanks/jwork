/**
 * Kelas untuk menyimpan informasi faktur ewallet (cashless)
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 010421
 */

public class EwalletPayment extends Invoice
{
    private static final PaymentType PAYMENT_TYPE = PaymentType.EwalletPayment;
    private Bonus bonus;

    public EwalletPayment(int id, Job job, String date, Jobseeker jobseeker,
                        InvoiceStatus invoiceStatus) {
        super(id, job, date, jobseeker, invoiceStatus);
        this.bonus = null;
    }
    public EwalletPayment(int id, Job job, String date, Jobseeker jobseeker,
                        Bonus bonus, InvoiceStatus invoiceStatus) {
        super(id, job, date, jobseeker, invoiceStatus);
        this.bonus = bonus;
    }
    public PaymentType getPaymentType() {
        return PAYMENT_TYPE;
    }
    public Bonus getBonus() {
        return bonus;
    }
    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }
    public void setTotalFee() {
        
        if (bonus != null && bonus.getActive() &&
            super.getTotalFee() > bonus.getMinTotalFee()) {
                super.totalFee += bonus.getExtraFee();
        }
        else {
            super.totalFee = super.getJob().getFee();
        }
    }
    public void printData() {
        // Print data secara keseluruhan
        System.out.println("====== Ewallet Payment ======");
        System.out.println("ID           : " + getId());
        System.out.println("Job          : " + getJob().getName());
        System.out.println("Date         : " + getDate());
        System.out.println("Seeker       : " + getJobseeker().getName());
        if (bonus.getReferralCode() != null &&
            bonus != null && bonus.getActive() &&
            super.getTotalFee() > bonus.getMinTotalFee()) {
            System.out.println("Referral Code: " + bonus.getReferralCode());
        }
        System.out.println("Fee          : " + totalFee);
        System.out.println("Status       : " + getInvoiceStatus().toString());
        System.out.println("Payment Type : " + PAYMENT_TYPE.toString());
    }
}
