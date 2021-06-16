package RamadhanKalih.jwork;

import javax.net.ssl.HandshakeCompletedEvent;

import org.apache.catalina.startup.RealmRuleSet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JWork
{
    public static void main(String[] args) {
        
        Location loc1 = new Location("Jawa Barat", "Depok", "Headquarter Facebook");
        Location loc2 = new Location("Sumatera Selatan", "Palembang", "Gudang Pempek");
        Location loc3 = new Location("Bali", "Denpasar", "Travel Outlet");
        
        DatabaseRecruiter.addRecruiter(new Recruiter(1, "Geraldy", "geraldy@gmail.com", "0119201254", loc1));
        DatabaseRecruiter.addRecruiter(new Recruiter(2, "Hansaka", "hansaka@gmail.com", "0119201254", loc2));
        DatabaseRecruiter.addRecruiter(new Recruiter(3, "Mustofa", "mustofa@gmail.com", "0119201254", loc3));

        try {
            // kategori job yang sama
            DatabaseJob.addJob(new Job(1, "Kompresi Data", DatabaseRecruiter.getRecruiterById(1), 201000, JobCategory.BackEnd));
            DatabaseJob.addJob(new Job(2, "Enkripsi Data", DatabaseRecruiter.getRecruiterById(2), 105000, JobCategory.BackEnd));
            // recruiter dengan id sama
            DatabaseJob.addJob(new Job(3, "Manage API Google", DatabaseRecruiter.getRecruiterById(3), 50000, JobCategory.WebDeveloper));
            DatabaseJob.addJob(new Job(4, "Research Database", DatabaseRecruiter.getRecruiterById(3), 70000, JobCategory.DataAnalyst));
        }
        catch (RecruiterNotFoundException e) {
            return;
        }

        SpringApplication.run(JWork.class, args);
    }
}