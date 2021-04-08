/**
 * Kelas untuk menyimpan informasi Bonus
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 010421
 */

public class Bonus
{
    private int id;
    private String referralCode;
    private int extraFee;
    private int minTotalFee;
    private boolean active;

    /** ctor untuk inisialisasi variable */
    public Bonus(int id, String referralCode, int extraFee,
                int minTotalFee, boolean active) {
        this.id = id;
        this.referralCode = referralCode;
        this.extraFee = extraFee;
        this.minTotalFee = minTotalFee;
        this.active = active;
    }
    /**
     * mengakses informasi id dari bonus
     * @return int
     */
    public int getId() {
        return id;
    }
    /**
     * mengakses kode referral
     * @return String
     */
    public String getReferralCode() {
        return referralCode;
    }
    /**
     * mengakses bayaran extra dari bonus
     * @return int
     */
    public int getExtraFee() {
        return extraFee;
    }
    /**
     * mengakses bayaran job minimum untuk mengaktifkan bonus
     * @return int
     */
    public int getMinTotalFee() {
        return minTotalFee;
    }
    /**
     * mengakses validasi / aktifnya bonus
     * @return boolean
     */
    public boolean getActive() {
        return active;
    }
    /**
     * mutasi id dari bonus
     * @param int
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * mutasi kode referral dari bonus
     * @param String
     */
    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }
    /**
     * mutasi bayaran extra untuk bonus
     * @param int
     */
    public void setExtraFee(int extraFee) {
        this.extraFee = extraFee;
    }
    /**
     * mutasi bayaran job minimum yang diperlukan agar bonus aktif
     * @param int
     */
    public void setMinTotalFee(int minTotalFee) {
        this.minTotalFee = minTotalFee;
    }
    /**
     * mutasi valid / aktifnya bonus
     * @param boolean
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    public String toString() {
        return  "============ BONUS ============" +
                "\nId = " + id +
                "\nReferral Code = " + referralCode +
                "\nExtra Fee = " + extraFee +
                "\nMin Total Fee = " + minTotalFee +
                "\nActive Status = " + active;
    }
}
