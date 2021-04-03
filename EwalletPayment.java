/**
 * Kelas menyimpan informasi faktur pembayarnan ewallet (cashless)
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 010421
 * @inheritDoc
 */

public class EwalletPayment extends Invoice
{
    /** tipe pembayaran sesuai enum @PaymentType */
    private static final PaymentType PAYMENT_TYPE = PaymentType.EwalletPayment;
    /** bonus berupa bayaran extra pada total fee */
    private Bonus bonus;

    /** ctor tanpa bonus */
    public EwalletPayment(int id, Job job, String date, Jobseeker jobseeker,
                        InvoiceStatus invoiceStatus) {
        super(id, job, date, jobseeker, invoiceStatus);
        this.bonus = null;
    }
    /** ctor dengan bonus */
    public EwalletPayment(int id, Job job, String date, Jobseeker jobseeker,
                        Bonus bonus, InvoiceStatus invoiceStatus) {
        super(id, job, date, jobseeker, invoiceStatus);
        this.bonus = bonus;
    }
    /**
     * mengakses tipe pembayaran @Invoice sesuai enum @PaymentType
     * @return PaymentType
     */
    public PaymentType getPaymentType() {
        return PAYMENT_TYPE;
    }
    /**
     * mengakses bonus pada kelas ini
     * @return Bonus
     */
    public Bonus getBonus() {
        return bonus;
    }
    /**
     * mutasi bonus pada kelas ini
     * @param Bonus
     */
    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }
    /** memperbarui total fee berdasarkan bonus */
    public void setTotalFee() {
        super.totalFee = getJob().getFee();
        if (bonus != null && bonus.getActive() &&
            getTotalFee() > bonus.getMinTotalFee()) {
            super.totalFee += bonus.getExtraFee();
        }
    }
    /** mencetak informasi pembayaran ewallet ke terminal */
    public void printData() {
        // Print data secara keseluruhan
        System.out.println("====== Ewallet Payment ======");
        System.out.println("ID           : " + getId());
        System.out.println("Job          : " + getJob().getName());
        System.out.println("Date         : " + getDate());
        System.out.println("Seeker       : " + getJobseeker().getName());
        if (bonus.getReferralCode() != null &&
            bonus != null && bonus.getActive() &&
            getTotalFee() > bonus.getMinTotalFee()) {
            System.out.println("Referral Code: " + bonus.getReferralCode());
        }
        System.out.println("Fee          : " + totalFee);
        System.out.println("Status       : " + getInvoiceStatus().toString());
        System.out.println("Payment Type : " + PAYMENT_TYPE.toString());
    }
}
