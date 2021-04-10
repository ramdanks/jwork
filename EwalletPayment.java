import java.text.SimpleDateFormat;
import java.util.Calendar;

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
    public EwalletPayment(int id, Job job, Jobseeker jobseeker,
                        InvoiceStatus invoiceStatus) {
        super(id, job, jobseeker, invoiceStatus);
        this.bonus = null;
    }
    /** ctor dengan bonus */
    public EwalletPayment(int id, Job job, Jobseeker jobseeker,
                        Bonus bonus, InvoiceStatus invoiceStatus) {
        super(id, job, jobseeker, invoiceStatus);
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
    public String toString() {
        Calendar date = getDate();
        String strDate = "";
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            strDate = dateFormat.format(date);
        }
        // Print data secara keseluruhan
        String str =    "====== Ewallet Payment ======" +
                        "\nID           : " + getId() +
                        "\nJob          : " + getJob().getName() +
                        "\nDate         : " + strDate +
                        "\nSeeker       : " + getJobseeker().getName();
        // Hanya tampilkan referral code kalau bonusnya aktif atau valid
        if (bonus.getReferralCode() != null &&
            bonus != null && bonus.getActive() &&
            getTotalFee() > bonus.getMinTotalFee()) {
            str += "\nReferral Code: " + bonus.getReferralCode();
        }
        str +=          "\nFee          : " + totalFee +
                        "\nStatus       : " + getInvoiceStatus().toString() +
                        "\nPayment Type : " + PAYMENT_TYPE.toString();
        return str;
    }
}
