/**
 * Kelas untuk menyimpan database kelas Bonus
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 010421
 */

package RamadhanKalih.jwork;
import java.util.ArrayList;

public class DatabaseBonus
{
    /** menyimpan list Bonus dari namanya*/
    private static ArrayList<Bonus> BONUS_DATABASE = new ArrayList<Bonus>();
    private static int lastId = 0;

    /**
     * meminta seluruh list bonus dari database
     * @return String[]
     */
    public static ArrayList<Bonus> getBonusDatabase() {
        return BONUS_DATABASE;
    }
    public static int getLastId() {
        return lastId;
    }
    public static Bonus getBonusById(int id) throws BonusNotFoundException {
        for (Bonus b : BONUS_DATABASE)
            if (b.getId() == id)
                return b;
        throw new BonusNotFoundException(id);
    }
    public static Bonus getBonusByReferralCode(String referralCode) {
        for (Bonus b : BONUS_DATABASE)
            if (b.getReferralCode().equals(referralCode))
                return b;
        return null;
    }
    /**
     * menambah bonus ke database
     * @param Bonus
     * @return boolean
     */
    public static boolean addBonus(Bonus bonus) throws ReferralCodeAlreadyExistsException {
        for (Bonus b : BONUS_DATABASE)
        {
            if (b.getId() == bonus.getId())
                return false;
            if (b.getReferralCode() == bonus.getReferralCode()) 
                throw new ReferralCodeAlreadyExistsException(bonus);
        }
        BONUS_DATABASE.add(bonus);
        lastId = bonus.getId();
        return true;
    }
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
