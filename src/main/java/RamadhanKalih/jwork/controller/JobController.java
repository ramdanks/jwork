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
        try {
            return DatabaseJobPostgre.getAllJob();
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    @RequestMapping("/{id}")
    public Job getJobById(@PathVariable int id) {
        try {
            return DatabaseJobPostgre.getJob(id);
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    @RequestMapping(value="/recruiter/{id}", method = RequestMethod.GET)
    public ArrayList<Job> getJobByRecruiter(@PathVariable int id)
    {
        try {
            return DatabaseJobPostgre.getJobByRecruiter(id);
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    @RequestMapping(value="/category/{category}", method = RequestMethod.GET)
    public ArrayList<Job> getJobByCategory(@PathVariable JobCategory category)
    {
        try {
            return DatabaseJobPostgre.getJobByCategory(category);
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public Job addJob(  @RequestParam(value="name") String name,
                        @RequestParam(value="recruiterId") int recruiterId,
                        @RequestParam(value="fee") int fee,
                        @RequestParam(value="category") JobCategory category)
    {
        try {
            DatabaseJobPostgre.insertJob(name, recruiterId, fee, category);
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }
}