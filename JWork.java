/**
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210320
 */

public class JWork
{
    public static void main(String[] args) {
        Location rloc = new Location("Jawa Barat", "Depok", "Lahir");
        Recruiter rec = new Recruiter(69, "Ramadhan", "ramadhan.kalih@ui.ac.id", "0812", rloc);
        Job job = new Job(1, "Programmer", rec, 10000, JobCategory.DataScientist);
        job.printData();
    }
}
