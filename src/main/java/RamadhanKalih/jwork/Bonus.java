package RamadhanKalih.jwork;

/** Kelas untuk menyimpan informasi Bonus
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210401
 */
public class Bonus
{
    /** nomor unik dari bonus */
    private int id;
    /** referral code dari bonus */
    private String referralCode;
    /** extra fee dari bonus */
    private int extraFee;
    /** fee minimum yang dibutuhkan agar extraFee dapat digunakan */
    private int minTotalFee;
    /** status bonus akan menentukan extraFee untuk dapat digunakan */
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

    /** mengakses informasi id dari bonus
     * @return id bonus
     */
    public int getId() {
        return id;
    }

    /** mengakses kode referral
     * @return kode referral dari bonus
     */
    public String getReferralCode() {
        return referralCode;
    }

    /** mengakses bayaran extra dari bonus
     * @return extra fee bonus
     */
    public int getExtraFee() {
        return extraFee;
    }

    /** mengakses bayaran job minimum untuk mengaktifkan bonus
     * @return minimum total fee bonus
     */
    public int getMinTotalFee() {
        return minTotalFee;
    }

    /** mengakses status keaktifan bonus
     * @return status bonus
     */
    public boolean getActive() {
        return active;
    }

    /** mutasi id dari bonus
     * @param id id yang baru
     */
    public void setId(int id) {
        this.id = id;
    }

    /** mutasi kode referral dari bonus
     * @param referralCode kode referral yang baru
     */
    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    /** mutasi bayaran extra untuk bonus
     * @param extraFee extra fee yang baru
     */
    public void setExtraFee(int extraFee) {
        this.extraFee = extraFee;
    }

    /** mutasi bayaran job minimum yang diperlukan agar bonus aktif
     * @param minTotalFee minimum total fee yang baru
     */
    public void setMinTotalFee(int minTotalFee) {
        this.minTotalFee = minTotalFee;
    }

    /** mutasi status keaktifan bonus
     * @param active status yang baru
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /** mengembalikan seluruh informasi yang disimpan dalam kelas */
    public String toString() {
        return  "============ BONUS ============" +
                "\nId = " + id +
                "\nReferral Code = " + referralCode +
                "\nExtra Fee = " + extraFee +
                "\nMin Total Fee = " + minTotalFee +
                "\nActive Status = " + active;
    }
}
