package RamadhanKalih.jwork.controller;

import RamadhanKalih.jwork.*;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

/** Controller untuk menghandle REST objek Bonus dan memprosesnya ke dbms psql
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210617
 */
@RequestMapping("/bonus")
@RestController
public class BonusController
{
    /** meminta seluruh Bonus di database
     * @return Bonus dalam database, jika kosong maka return list kosong,
     * jika terdapat error koneksi atau saat mengurai data maka return null
     */
    @RequestMapping("")
    public ArrayList<Bonus> getAllBonus() {
        try {
            return DatabaseBonusPostgre.getAllBonus();
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    /** meminta Bonus berdasarkan kode referral-nya 
     * @param referralCode kode referral yang dicari
     * @return Bonus sesuai parameter, jika tidak ditemukan return null
     */
    @RequestMapping("/{referralCode}")
    public Bonus getBonusByReferralCode(@PathVariable String referralCode) {
        try {
            return DatabaseBonusPostgre.getBonusByReferral(referralCode);
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    /** menambah Bonus ke database
     * @param referralCode kode referral Bonus
     * @param extraFee extra fee Bonus
     * @param minTotalFee minimum total fee Bonus
     * @param active status keaktifan Bonus
     * @return Bonus yang terdaftar, jika melanggar aturan dbms maka return null
     */
    @RequestMapping(value="/", method = RequestMethod.POST)
    public Bonus addBonus(  @RequestParam(value="referralCode") String referralCode,
                            @RequestParam(value="extraFee") int extraFee,
                            @RequestParam(value="minTotalFee") int minTotalFee,
                            @RequestParam(value="active") boolean active)
    {
        try {
            int bonusId = DatabaseBonusPostgre.insertBonus(referralCode, extraFee, minTotalFee, active);
            return new Bonus(bonusId, referralCode, extraFee, minTotalFee, active);
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }
}