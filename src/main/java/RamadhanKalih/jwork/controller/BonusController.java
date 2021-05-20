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
        return DatabaseBonus.getBonusDatabase();
    }

    @RequestMapping("/{referralCode}")
    public Bonus getBonusByReferralCode(@PathVariable String referralCode) {
        return DatabaseBonus.getBonusByReferralCode(referralCode);
    
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public Bonus addBonus(  @RequestParam(value="referralCode") String referralCode,
                            @RequestParam(value="extraFee") int extraFee,
                            @RequestParam(value="minTotalFee") int minTotalFee,
                            @RequestParam(value="active") boolean active)
    {
        int newId = DatabaseBonus.getLastId() + 1;
        Bonus var = new Bonus(newId, referralCode, extraFee, minTotalFee, active);
        try {
            DatabaseBonus.addBonus(var);
        } catch (ReferralCodeAlreadyExistsException e) {
            System.out.println(e.getMessage());
            var = null;
        }
        return var;
    }
}