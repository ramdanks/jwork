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
        return DatabaseRecruiter.getRecruiterDatabase();
    }

    @RequestMapping("/{id}")
    public Recruiter getRecruiterById(@PathVariable int id) {
        Recruiter var = null;
        try {
            var = DatabaseRecruiter.getRecruiterById(id);
        } catch (RecruiterNotFoundException e) {
            System.out.println(e.getMessage());
            var = null;
        }
        return var;
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public Recruiter addRecruiter(  @RequestParam(value="name") String name,
                                    @RequestParam(value="email") String email,
                                    @RequestParam(value="phoneNumber") String phoneNumber,
                                    @RequestParam(value="province") String province,
                                    @RequestParam(value="city") String city,
                                    @RequestParam(value="description") String description)
    {
        Location loc = new Location(province, city, description);
        int newId = DatabaseRecruiter.getLastId() + 1;
        Recruiter var = new Recruiter(newId, name, email, phoneNumber, loc);
        DatabaseRecruiter.addRecruiter(var);
        return var;
    }
}