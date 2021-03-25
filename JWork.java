/**
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210320
 */

public class JWork
{
    public static void main(String[] args) {
        Location rloc = new Location("Jawa Barat", "Depok", "Lahir");
        Recruiter rec = new Recruiter(69, "Ramadhan", "ramadhan.kalih@ui.ac.id", "0812", rloc);
        Job job = new Job(1, "Programmer", rec, 10000, "Desktop App");
        
        Location sloc = new Location("Jawa Timur", "Surabaya", "Lahir");
        Jobseeker js = new Jobseeker(1, "Orang", "orang@ui.ac.id", "1234", "19 March 2021", sloc);
        
        Invoice inv = new Invoice(1, 1, "19 March 2021", 6000, js);
        
        System.out.println("Recruiter: " + rec.getName());
        rec.setName("Geraldy");
        System.out.println("Recruiter: " + rec.getName());
        job.printData();
        
        DatabaseJob.addJob(job);
        DatabaseRecruiter.addRecruiter(rec);
    }
}
