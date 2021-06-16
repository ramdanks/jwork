package RamadhanKalih.jwork.controller;

import RamadhanKalih.jwork.*;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RequestMapping("/recruiter")
@RestController
public class RecruiterController
{

    @RequestMapping("")
    public ArrayList<Recruiter> getAllRecruiter() {
        try {
            return DatabaseRecruiterPostgre.getAllRecruiter();
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    @RequestMapping("/{id}")
    public Recruiter getRecruiterById(@PathVariable int id) {
        try {
            return DatabaseRecruiterPostgre.getRecruiter(id);
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public Recruiter addRecruiter(  @RequestParam(value="name") String name,
                                    @RequestParam(value="email") String email,
                                    @RequestParam(value="phoneNumber") String phoneNumber,
                                    @RequestParam(value="province") String province,
                                    @RequestParam(value="city") String city,
                                    @RequestParam(value="description") String description)
    {
        try {
            DatabaseRecruiterPostgre.insertRecruiter(name, email, phoneNumber, province, city, description);
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }
}