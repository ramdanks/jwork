package RamadhanKalih.jwork.controller;

import RamadhanKalih.jwork.*;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

/** Controller untuk menghandle REST objek Job dan memprosesnya ke dbms psql
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210617
 */
@RequestMapping("/job")
@RestController
public class JobController
{
    /** meminta seluruh Job di database
     * @return Job dalam database, jika kosong maka return list kosong,
     * jika terdapat error koneksi atau saat mengurai data maka return null
     */
    @RequestMapping("")
    public ArrayList<Job> getAllJob() {
        try {
            return DatabaseJobPostgre.getAllJob();
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    /** meminta Job berdasarkan id-nya 
     * @param id Job yang akan dicari
     * @return Job yang diminta, jika tidak ditemukan return null
     */
    @RequestMapping("/{id}")
    public Job getJobById(@PathVariable int id) {
        try {
            return DatabaseJobPostgre.getJob(id);
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    /** meminta Job berdasarkan recruiter-nya
     * @param id nomor id recruiter
     * @return list Job yang terikat dengan recruiter parameter, jika kosong return empty list, 
     * jika terdapat error koneksi atau saat mengurai data maka return null
     */
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

    /** meminta Job berdasarkan kategori-nya
     * @param category ketegori Job
     * @return list Job yang terikat dengan kategori parameter, jika kosong return empty list, 
     * jika terdapat error koneksi atau saat mengurai data maka return null
     */
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

    /** mendaftarkan Job ke database
     * @param name nama Job
     * @param recruiterId nomor id recruiter Job
     * @param fee fee Job
     * @param category kategori Job
     * @return Job yang terdaftar, apabila tidak sesuai aturan dbms maka return null,
     * jika terdapat error koneksi atau saat mengurai data maka return null
     */
    @RequestMapping(value="/", method = RequestMethod.POST)
    public Job addJob(  @RequestParam(value="name") String name,
                        @RequestParam(value="recruiterId") int recruiterId,
                        @RequestParam(value="fee") int fee,
                        @RequestParam(value="category") JobCategory category)
    {
        try {
            Recruiter rec = DatabaseRecruiterPostgre.getRecruiter(recruiterId);
            int jobId = DatabaseJobPostgre.insertJob(name, recruiterId, fee, category);
            return new Job(jobId, name, rec, fee, category);
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }
}