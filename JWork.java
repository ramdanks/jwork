/**
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 010421
 */

import java.util.ArrayList;
import java.util.function.Consumer;

public class JWork
{
    public static void main(String[] args) {

        String date = "01-04-2021";
        InvoiceStatus status = InvoiceStatus.OnGoing;

        Jobseeker js = new Jobseeker(100, "Ramadhan", "@ui", "1234", date);
        Location loc = new Location("Jawa Barat", "Depok", "Tempat Kerja");
        Recruiter rc = new Recruiter(2, "Geraldy", "g@ui.ac.id", "1234", loc);
        Job job = new Job(69, "Tester", rc, 70000, JobCategory.BackEnd);
        
        // tanpa referral
        Bonus bonus1 = new Bonus(1, null, 100, 10000, true);
        // dengan referral job totalFee < bonus minTotalFee
        Bonus bonus2 = new Bonus(3, "MSJOB", 100, 90000, true);
        // dengan referral job totalFee > bonus minTotalFee
        Bonus bonus3 = new Bonus(2, "MSJOB", 100, 5000, true);

        ArrayList<EwalletPayment> arrEP = new ArrayList<EwalletPayment>();
        arrEP.add(new EwalletPayment(1, job, date, js, bonus1, status));
        arrEP.add(new EwalletPayment(2, job, date, js, bonus2, status));
        arrEP.add(new EwalletPayment(3, job, date, js, bonus3, status));

        arrEP.forEach((ep) -> {
            ep.setTotalFee();
            ep.printData();
        });
    }
}
