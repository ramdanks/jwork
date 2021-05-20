package RamadhanKalih.jwork.controller;

import RamadhanKalih.jwork.*;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RequestMapping("/job")
@RestController
public class JobController
{

    @RequestMapping("")
    public ArrayList<Job> getAllJob() {
        return DatabaseJob.getJobDatabase();
    }

    @RequestMapping("/{id}")
    public Job getJobById(@PathVariable int id) {
        Job var = null;
        try {
            var = DatabaseJob.getJobById(id);
        } catch (JobNotFoundException e) {
            System.out.println(e.getMessage());
            var = null;
        }
        return var;
    }

    @RequestMapping(value="/recruiter/{id}", method = RequestMethod.GET)
    public ArrayList<Job> getJobByRecruiter(@PathVariable int id)
    {
        return DatabaseJob.getJobByRecruiter(id);
    }

    @RequestMapping(value="/category/{category}", method = RequestMethod.GET)
    public ArrayList<Job> getJobByCategory(@PathVariable JobCategory category)
    {
        return DatabaseJob.getJobByCategory(category);
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public Job addJob(  @RequestParam(value="name") String name,
                        @RequestParam(value="recruiterId") int recruiterId,
                        @RequestParam(value="fee") int fee,
                        @RequestParam(value="category") JobCategory category)
    {
        Job var = null;
        try {
            Recruiter r = DatabaseRecruiter.getRecruiterById(recruiterId);
            int newId = DatabaseJob.getLastId() + 1;
            var = new Job(newId, name, r, fee, category);
            DatabaseJob.addJob(var);
        } catch (RecruiterNotFoundException e) {
            System.out.println(e.getMessage());
            var = null;
        }
        return var;
    }
}