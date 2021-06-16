package RamadhanKalih.jwork;

/** Kelas exception untuk bonus yang tidak ditemukan
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210517
 */
public class BonusNotFoundException extends Exception
{
    /** nomor id bonus yang tidak ditemukan */
    private int bonus_error;

    /** ctor inisiasi variabel
     * @param bonus_input nomor id dari bonus yang tidak ditemukan
     */
    public BonusNotFoundException(int bonus_input) {
        super("Bonus ID: ");
        bonus_error = bonus_input;
    }
    
    /** mengembalikan keterangan bonus id yang tidak ditemukan */
    public String getMessage() {
        return super.getMessage() + bonus_error + " not found";
    }
}
