package RamadhanKalih.jwork.controller;

import RamadhanKalih.jwork.*;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/jobseeker")
@RestController
public class JobseekerController
{

    @RequestMapping("/{id}")
    public Jobseeker getJobseekerById(@PathVariable int id) {
        Jobseeker var = null;
        try {
            var = DatabaseJobseeker.getJobseekerById(id);
        } catch (JobseekerNotFoundException e) {
            System.out.println(e.getMessage());
            var = null;
        }
        return var;
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public Jobseeker registerJobseeker( @RequestParam(value="name") String name,
                                        @RequestParam(value="email") String email,
                                        @RequestParam(value="password") String password)
    {
        Jobseeker var = new Jobseeker(DatabaseJobseeker.getLastId() + 1, name, email, password);
        try {
            if (!DatabaseJobseeker.addJobseeker(var))
                throw new Exception("Object may violate constraint!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            var = null;
        }
        return var;
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public Jobseeker loginJobseeker(@RequestParam(value="email") String email,
                                    @RequestParam(value="password") String password)
    {
        return DatabaseJobseeker.getJobseekerLogin(email, password);
    }
}