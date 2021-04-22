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
        Location loc = new Location("Jawa Barat", "Depok", "Headquarter FB");

        {
            Recruiter rec = new Recruiter(0, "Mark Zuckerberg", "g@ui.ac.id", "12312031", loc);     
            DatabaseRecruiter.addRecruiter(rec);  
        }
       
        Jobseeker js1 = new Jobseeker(0, "Ramadhan", "ramadhanks1@gmail.com", "Power69Ranger", 2021, 1, 1);
        Jobseeker js2 = new Jobseeker(1, "Ramadhan", "ramadhanks1@gmail.com", "Power69Ranger", 2021, 1, 1);
        Jobseeker js3 = new Jobseeker(2, "Geraldy", "geraldy@ui.ac.id", "Power69Ranger", 2021, 1, 1);
        DatabaseJobseeker.addJobseeker(js1);
        DatabaseJobseeker.addJobseeker(js2);
        DatabaseJobseeker.addJobseeker(js3);

        {
            ArrayList<Jobseeker> test = DatabaseJobseeker.getDatabaseJobseeker();
            for (Jobseeker js : test)
                System.out.println(js.toString());
        }

        Job job1 = new Job(0, "Hosting dan Maintenance", DatabaseRecruiter.getRecruiterById(0), 10000, JobCategory.BackEnd);
        Job job2 = new Job(1, "Database Builder", DatabaseRecruiter.getRecruiterById(0), 10000, JobCategory.BackEnd);
        Job job3 = new Job(2, "Membangun Web", DatabaseRecruiter.getRecruiterById(0), 10000, JobCategory.FrontEnd);
        DatabaseJob.addJob(job1);
        DatabaseJob.addJob(job2);
        DatabaseJob.addJob(job3);

        {
            ArrayList<Job> test = DatabaseJob.getJobByCategory(JobCategory.BackEnd);
            for (Job j : test)
                System.out.println(j.toString());
        }
    }
}
