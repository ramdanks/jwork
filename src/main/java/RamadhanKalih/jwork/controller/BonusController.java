package RamadhanKalih.jwork.controller;

import RamadhanKalih.jwork.*;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RequestMapping("/bonus")
@RestController
public class BonusController
{

    @RequestMapping("")
    public ArrayList<Bonus> getAllBonus() {
        try {
            return DatabaseBonusPostgre.getAllBonus();
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    @RequestMapping("/{referralCode}")
    public Bonus getBonusByReferralCode(@PathVariable String referralCode) {
        try {
            return DatabaseBonusPostgre.getBonusByReferral(referralCode);
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

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