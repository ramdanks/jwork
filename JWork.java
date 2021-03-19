/**
* @Author: Ramadhan Kalih Sewu (1806148826)
* @Version: 210304
*/

public class JWork
{
    public static void main(String[] args) {
        Location aloc = new Location("Nusa Tenggara Barat", "Lombok", "Wow");
        Location rloc = new Location("Jawa Barat", "Depok", "Lab");
        Recruiter rec = new Recruiter(69, "Geraldy", "geraldy@ui.ac.id", "0812", rloc);
        Jobseeker js = new Jobseeker(1, "Ramadhan", "ramadhan@ui.ac.id", "1234", "19 March 2021", aloc);
        Job job = new Job(1, "Programmer", rec, 10000, "Desktop App");
        Invoice inv = new Invoice(1, 1, "19 March 2021", 6000, js);
        
        job.printData();
        inv.printData();
    }
}
