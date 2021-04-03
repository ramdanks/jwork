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
        InvoiceStatus status = InvoiceStatus.Finished;

        Jobseeker js = new Jobseeker(100, "Ramadhan", "@ui", "1234", date);
        Location loc = new Location("Jawa Barat", "Depok", "Tempat Kerja");
        Recruiter rc = new Recruiter(2, "Geraldy", "g@ui.ac.id", "1234", loc);
        Job job = new Job(69, "Tester", rc, 70000, JobCategory.BackEnd);

        ArrayList<BankPayment> arrEP = new ArrayList<BankPayment>();
        arrEP.add(new BankPayment(1, job, date, js, status));
        arrEP.add(new BankPayment(2, job, date, js, status, 1000));

        arrEP.forEach((ep) -> {
            ep.setTotalFee();
            ep.printData();
        });
    }
}
