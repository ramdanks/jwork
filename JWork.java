/**
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 030421
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale.Category;

public class JWork
{
    public static void main(String[] args) {

        Calendar cal = new GregorianCalendar(2021, 4, 8);
        Jobseeker js = new Jobseeker(1, "Ramadhan", "ramadhanks1@gmail.com", "Power69Ranger", 2021, 1, 1);
        Bonus bonus = new Bonus(1, "RS12JD", 1000, 10000, true);
        Location loc = new Location("Jawa Barat", "Depok", "Kerja");
        Recruiter rec = new Recruiter(1, "Geraldy", "g@ui.ac.id", "12312031", loc);
        Job job = new Job(1, "Tester", rec, 10000, JobCategory.BackEnd);

        ArrayList<Invoice> arrInv = new ArrayList<Invoice>();
        arrInv.add(new EwalletPayment(1, job, js, bonus, InvoiceStatus.OnGoing));
        arrInv.add(new BankPayment(1, job, js, InvoiceStatus.OnGoing));

        arrInv.forEach((inv) -> { System.out.println(inv.toString()); });
    }
}
