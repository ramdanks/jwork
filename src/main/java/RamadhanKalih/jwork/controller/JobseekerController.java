package RamadhanKalih.jwork.controller;

import RamadhanKalih.jwork.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.*;

/** Controller untuk menghandle REST objek Jobseeker dan memprosesnya ke dbms psql
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210617
 */
@RequestMapping("/jobseeker")
@RestController
public class JobseekerController
{
    /** regex pattern for jobseeker email */
    private static final Pattern pte = Pattern.compile(Jobseeker.EMAIL_PATTERN);
    /** regex pattern for jobseeker password */
    private static final Pattern ptp = Pattern.compile(Jobseeker.PASSWORD_PATTERN);

    /** meminta Jobseeker berdasarkan id-nya 
     * @param id Jobseeker yang akan dicari
     * @return Jobseeker yang diminta, jika tidak ditemukan return null
     */
    @RequestMapping("/{id}")
    public Jobseeker getJobseekerById(@PathVariable int id) {
        try {
            return DatabaseJobseekerPostgre.getJobseeker(id);
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    /** mendaftarkan Jobseeker ke database
     * @param name nama Jobseeker
     * @param email email Jobseeker (harus unik)
     * @param password password Jobseeker
     * @return Jobseeker yang terdaftar, apabila tidak sesuai aturan dbms maka return null,
     * apabila email dan password tidak sesuai regex jobseeker return null
     */
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public Jobseeker registerJobseeker( @RequestParam(value="name") String name,
                                        @RequestParam(value="email") String email,
                                        @RequestParam(value="password") String password)
    {
        try {
            // validasi regex
            if (!pte.matcher(email).matches() || !ptp.matcher(password).matches())
                return null;
            // proses input ke database
            int jobseekerId = DatabaseJobseekerPostgre.insertJobseeker(name, email, password);
            return new Jobseeker(jobseekerId, name, email, password);
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    /** login Jobseeker yang sudah terdaftar dalam database
     * @param email email Jobseeker
     * @param password password Jobseeker
     * @return Jobseeker yang sesuai, jika tidak maka return null
     */
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