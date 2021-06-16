package RamadhanKalih.jwork;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/** Kelas menyimpan informasi faktur pembayarnan ewallet (cashless)
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 010421
 * @inheritDoc
 */
public class EwalletPayment extends Invoice
{
    /** tipe pembayaran sesuai enum @PaymentType */
    private static final PaymentType PAYMENT_TYPE = PaymentType.EwalletPayment;

    /** ctor tanpa bonus */
    public EwalletPayment(int id, ArrayList<Job> jobs, Jobseeker jobseeker) {
        super(id, jobs, jobseeker);
        super.bonus = null;
        super.type = PAYMENT_TYPE;
    }
    /** ctor dengan bonus */
    public EwalletPayment(int id, ArrayList<Job> job, Jobseeker jobseeker, Bonus bonus) {
        super(id, job, jobseeker);
        this.bonus = bonus;
        super.type = PAYMENT_TYPE;
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
        // perbarui total fee dengan fee jobs yang ada
        super.totalFee = 0;
        for(Job j : getJobs())
            super.totalFee += j.getFee();
        // tambahkan total fee dengan bonus jika memenuhi criteria
        // 1. bonus ada dan aktif
        // 2. bonus min total fee lebih dari total fee invoice
        if (bonus != null && bonus.getActive() &&
            getTotalFee() > bonus.getMinTotalFee()) {
            super.totalFee += bonus.getExtraFee();
        }
    }
    /** mencetak informasi pembayaran ewallet ke terminal */
    public String toString() {
        Date date = getDate().getTime();
        String strDate = "";
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            strDate = dateFormat.format(date);
        }
        // Print data secara keseluruhan
        String str =    "====== Ewallet Payment ======" +
                        "\nID           : " + getId() +
                        "\nJobs         : " + getJobs() +
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
