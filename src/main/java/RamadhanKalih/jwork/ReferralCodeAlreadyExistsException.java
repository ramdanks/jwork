package RamadhanKalih.jwork;

/** Kelas exception untuk kode referral (member Bonus) yang sudah terdaftar
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210517
 */
public class ReferralCodeAlreadyExistsException extends Exception
{
    /** Bonus yang bermasalah */
    private Bonus bonus_error;

    /** ctor inisiasi variabel */
    public ReferralCodeAlreadyExistsException(Bonus bonus_input) {
        super("Bonus Code: ");
        bonus_error = bonus_input;
    }
    /** mengembalikan keterangan kode referral bonus yang sudah terdaftar */
    public String getMessage() {
        return super.getMessage() + bonus_error.getReferralCode() + " already exists";
    }
}
