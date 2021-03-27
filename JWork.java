/**
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210320
 */

public class JWork
{
    public static void main(String[] args) {

        InvoiceStatus status = InvoiceStatus.OnGoing;
        PaymentType type = PaymentType.BankPayment;

        Location loc = new Location("DKI Jakarta", "Jakarta Timur", "Kantor");
        Jobseeker js = new Jobseeker(1, "Ramadhan", "r@ui.ac.id", "pass", "27-03-2021");
        Recruiter rc = new Recruiter(2, "Geraldy", "g@ui.ac.id", "1234", loc);
        Job job = new Job(69, "Tester", rc, 70000, JobCategory.BackEnd);

        Invoice inv = new Invoice(1, job.getId(), "27-03-2021", job.getFee(), js, type, status);

        inv.printData();

    }
}
