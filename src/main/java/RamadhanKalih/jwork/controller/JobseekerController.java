package RamadhanKalih.jwork.controller;

import RamadhanKalih.jwork.*;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/jobseeker")
@RestController
public class JobseekerController
{

    @RequestMapping("/{id}")
    public Jobseeker getJobseekerById(@PathVariable int id) {
        try {
            return DatabaseJobseekerPostgre.getJobseeker(id);
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public Jobseeker registerJobseeker( @RequestParam(value="name") String name,
                                        @RequestParam(value="email") String email,
                                        @RequestParam(value="password") String password)
    {
        try {
            DatabaseJobseekerPostgre.insertJobseeker(name, email, password);
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public Jobseeker loginJobseeker(@RequestParam(value="email") String email,
                                    @RequestParam(value="password") String password)
    {
        try {
            return DatabaseJobseekerPostgre.getJobseeker(email, password);
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }
}