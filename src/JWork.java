/**
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210424
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class JWork
{
    public static void main(String[] args) {

        ArrayList<Job> list1 = new ArrayList<Job>();
        ArrayList<Job> list2 = new ArrayList<Job>();

        {
            DatabaseBonus.addBonus(new Bonus(0, "MYCODE", 1000, 10000, false));
            DatabaseBonus.addBonus(new Bonus(1, "MYCODE", 1000, 10000, true));
        }

        {
            ArrayList<Bonus> list = DatabaseBonus.getBonusDatabase();
            for (Bonus b : list)
                System.out.println(b.toString());
        }

        {
            Jobseeker js1 = new Jobseeker(0, "Ramadhan", "ramadhanks1@gmail.com", "Power69Ranger", 2021, 1, 1);
            Jobseeker js2 = new Jobseeker(1, "Ramadhan", "ramadhanks1@gmail.com", "Power69Ranger", 2021, 1, 1);
            Jobseeker js3 = new Jobseeker(2, "Geraldy", "geraldy@ui.ac.id", "Power69Ranger", 2021, 1, 1);
            DatabaseJobseeker.addJobseeker(js1);
            DatabaseJobseeker.addJobseeker(js2);
            DatabaseJobseeker.addJobseeker(js3);
        }

        {
            list1.add(new Job(0, "Hosting dan Maintenance", DatabaseRecruiter.getRecruiterById(0), 20000, JobCategory.BackEnd));
            list2.add(new Job(1, "Database Builder", DatabaseRecruiter.getRecruiterById(0), 5000, JobCategory.FrontEnd));
        
            DatabaseInvoice.addInvoice(new EwalletPayment(0, list1, DatabaseJobseeker.getJobseekerById(1), DatabaseBonus.getBonusById(1)));
        }

        {
            int lastId = DatabaseJobseeker.getLastId();
            ArrayList<Invoice> list = DatabaseInvoice.getInvoiceByJobseeker(lastId);
            for (Invoice i : list)
                i.setTotalFee();
        }

        {
            DatabaseInvoice.addInvoice(new BankPayment(1, list2, DatabaseJobseeker.getJobseekerById(1)));
        }
        
        {
            int lastId = DatabaseJobseeker.getLastId();
            ArrayList<Invoice> list = DatabaseInvoice.getInvoiceByJobseeker(lastId);
            for (Invoice i : list)
                i.setTotalFee();
        }

        {
            ArrayList<Invoice> list = DatabaseInvoice.getInvoiceDatabase();
            for (Invoice i : list)
                System.out.println(i.toString());
        }

        {
            int lastId = DatabaseJobseeker.getLastId();
            ArrayList<Invoice> list = DatabaseInvoice.getInvoiceByJobseeker(lastId);
            for (Invoice i : list)
                i.setInvoiceStatus(InvoiceStatus.Finished);
        }

        {
            DatabaseInvoice.addInvoice(new EwalletPayment(1, list1, DatabaseJobseeker.getJobseekerById(2), DatabaseBonus.getBonusById(1)));
        }

        {
            ArrayList<Bonus> list = DatabaseBonus.getBonusDatabase();
            for (Bonus b : list)
                b.setActive(true);
        }

        {
            ArrayList<Invoice> list = DatabaseInvoice.getInvoiceDatabase();
            for (Invoice i : list)
            {
                i.setTotalFee();
                System.out.println(i.toString());
            }
        }
    }
}
