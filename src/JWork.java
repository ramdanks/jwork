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

        // Tugas 8: EmailAlreadyExistsException
        {
            ArrayList<Jobseeker> list = new ArrayList<Jobseeker>();
            list.add(new Jobseeker(1, "Ramadhan", "rama@ui.ac.id", "Passw0rd1"));
            list.add(new Jobseeker(2, "Geraldy", "geraldy@ui.ac.id", "Passw0rd1"));
            list.add(new Jobseeker(3, "Ailsa", "ailsa@ui.ac.id", "Passw0rd1"));
            list.add(new Jobseeker(4, "Hacker", "email@ui.ac.id", "Passw0rd1"));
            list.add(new Jobseeker(5, "Yogie", "email@ui.ac.id", "Passw0rd1"));
    
            for (Jobseeker j : list)
            {
                try {
                    DatabaseJobseeker.addJobseeker(j);
                } catch (EmailAlreadyExistsException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        // Tugas 8: ReferralCodeAlreadyExistsException
        {
            ArrayList<Bonus> list = new ArrayList<Bonus>();
            list.add(new Bonus(1, "MYCODE", 10000, 20000, true));
            list.add(new Bonus(2, "MYCODE", 50000, 80000, false));
            for (Bonus b : list)
            {
                try {
                    DatabaseBonus.addBonus(b);
                } catch (ReferralCodeAlreadyExistsException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        
        // Tugas 8: Remaining Exception
        {
            try {
                Jobseeker js = DatabaseJobseeker.getJobseekerById(9);
            } catch (JobseekerNotFoundException e) {
                System.out.println(e.getMessage());
            }

            try {
                Recruiter js = DatabaseRecruiter.getRecruiterById(9);
            } catch (RecruiterNotFoundException e) {
                System.out.println(e.getMessage());
            }

            try {
                Job js = DatabaseJob.getJobById(9);
            } catch (JobNotFoundException e) {
                System.out.println(e.getMessage());
            }

            try {
                Bonus js = DatabaseBonus.getBonusById(9);
            } catch (BonusNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        // Tugas 8: Data yang masuk ke Database
        {
            ArrayList<Bonus> listBonus = DatabaseBonus.getBonusDatabase();
            ArrayList<Jobseeker> listJS = DatabaseJobseeker.getDatabaseJobseeker();

            for (Bonus b : listBonus)
                System.out.println(b);
            
            for (Jobseeker js : listJS)
                System.out.println(js);
        }

        // Post Test
        {
            try {
                Jobseeker js1 = DatabaseJobseeker.getJobseekerById(1);
                Jobseeker js2 = DatabaseJobseeker.getJobseekerById(2);
                
                BankPayment bp1 = new BankPayment(1, DatabaseJob.getJobDatabase(), js1);
                bp1.setInvoiceStatus(InvoiceStatus.Cancelled);
                BankPayment bp2 = new BankPayment(2, DatabaseJob.getJobDatabase(), js2);
                DatabaseInvoice.addInvoice(bp1); // status invoice: Cancelled (no exception)
                DatabaseInvoice.addInvoice(bp2); // status invoice: OnGoing (trigger exception)

            } catch (JobseekerNotFoundException e) {
                System.out.print(e.getMessage());
                return;
            } catch (OngoingInvoiceAlreadyExistsException e) {
                System.out.println(e.getMessage());
            }

            // Trigger InvoiceNotFoundException 
            try {
                DatabaseInvoice.removeInvoice(99);
            } catch (InvoiceNotFoundException e) {
                System.out.println(e.getMessage());
            }

            // Trigger InvoiceNotFoundException 
            try {
                DatabaseInvoice.getInvoiceById(99);
            } catch (InvoiceNotFoundException e) {
                System.out.println(e.getMessage());
            }
            
            // Print data yang berhasil masuk ke database
            ArrayList<Invoice> list = DatabaseInvoice.getInvoiceDatabase();
            for (Invoice i : list)
                System.out.println(i);
        }
    }
}
