package RamadhanKalih.jwork;
import java.util.ArrayList;

/** Kelas menyimpan database Bonus
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210517
 */
public class DatabaseBonus
{
    /** menyimpan list Bonus */
    private static ArrayList<Bonus> BONUS_DATABASE = new ArrayList<Bonus>();
    /** menyimpan nomor id Bonus yang terakhir berhasil masuk ke database melalui method addBonus() */
    private static int lastId = 0;

    /** meminta seluruh list bonus dari database
     * @return ArrayList<Bonus> seluruh Bonus yang disimpan dalam kelas ini
     */
    public static ArrayList<Bonus> getBonusDatabase() {
        return BONUS_DATABASE;
    }
    
    /** meminta nomor id Bonus yang terakhir berhasil masuk ke database melalui method addBonus()
     * @return int nomor id Bonus
     */
    public static int getLastId() {
        return lastId;
    }

    /** meminta bonus yang tersimpan dalam database kelas ini
     * @param id nomor id Bonus
     * @return Bonus sesuai dengan permintaan parameter
     * @throws BonusNotFoundException
     */
    public static Bonus getBonusById(int id) throws BonusNotFoundException {
        for (Bonus b : BONUS_DATABASE)
            if (b.getId() == id)
                return b;
        throw new BonusNotFoundException(id);
    }

    /** meminta bonus yang tersimpan dalam database kelas ini
     * @param referralCode kode referal Bonus
     * @return Bonus sesuai dengan permintaan parameter
     */
    public static Bonus getBonusByReferralCode(String referralCode) {
        for (Bonus b : BONUS_DATABASE)
            if (b.getReferralCode().equals(referralCode))
                return b;
        return null;
    }

    /** menyimpan bonus ke dalam database kelas ini
     * @param bonus Bonus yang ingin disimpan
     * @return boolean true jika berhasil, false jika di database terdapat Bonus dengan id yang sama
     * @throws ReferralCodeAlreadyExistsException terdapat bonus dalam database yang memiliki kode referral sama dengan parameter
     */
    public static boolean addBonus(Bonus bonus) throws ReferralCodeAlreadyExistsException {
        for (Bonus b : BONUS_DATABASE)
        {
            if (b.getId() == bonus.getId())
                return false;
            if (b.getReferralCode().equals(bonus.getReferralCode()))
                throw new ReferralCodeAlreadyExistsException(bonus);
        }
        BONUS_DATABASE.add(bonus);
        lastId = bonus.getId();
        return true;
    }

    /** menghapus bonus dalam database kelas ini
     * @param id nomor id Bonus yang ingin dihapus
     * @return boolean true jika berhasil menghapus Bonus yang diminta
     * @throws BonusNotFoundException tidak menemukan Bonus dalam database dengan nomor id parameter
     */
    public static boolean removeBonus(int id) throws BonusNotFoundException {
        for (int i = 0; i < BONUS_DATABASE.size(); i++)
        {
            if (BONUS_DATABASE.get(i).getId() == id)
            {
                BONUS_DATABASE.remove(i);
                return true;
            }
        }
        throw new BonusNotFoundException(id);
    }

    /** mengaktifkan status bonus dalam database kelas ini
     * @param id nomor id Bonus yang ingin diaktifkan
     * @return true jika menemukan bonus dengan parameter yang diminta, jika tidak return false
     */
    public static boolean activeBonus(int id) {
        for (int i = 0; i < BONUS_DATABASE.size(); i++)
        {
            if (BONUS_DATABASE.get(i).getId() == id)
            {
                BONUS_DATABASE.get(i).setActive(true);
                return true;
            }
        }
        return false;
    }

    /** menon-aktifkan status bonus dalam database kelas ini 
     * @param id nomor id Bonus yang ingin di non-aktifkan
     * @return true jika menemukan bonus dengan parameter yang diminta, jika tidak return false
     */
    public static boolean deactivateBonus(int id) {
        for (int i = 0; i < BONUS_DATABASE.size(); i++)
        {
            if (BONUS_DATABASE.get(i).getId() == id)
            {
                BONUS_DATABASE.get(i).setActive(false);
                return true;
            }
        }
        return false;
    }
}
