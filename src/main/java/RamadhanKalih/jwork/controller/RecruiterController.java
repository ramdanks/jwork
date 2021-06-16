package RamadhanKalih.jwork.controller;

import RamadhanKalih.jwork.*;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

/** Controller untuk menghandle REST objek Recruiter dan memprosesnya ke dbms psql
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210617
 */
@RequestMapping("/recruiter")
@RestController
public class RecruiterController
{
    /** meminta seluruh Recruiter di database
     * @return Recruiter dalam database, jika kosong maka return list kosong,
     * jika terdapat error koneksi atau saat mengurai data maka return null
     */
    @RequestMapping("")
    public ArrayList<Recruiter> getAllRecruiter() {
        try {
            return DatabaseRecruiterPostgre.getAllRecruiter();
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    /** meminta Recruiter di database dengan id
     * @param id nomor id Recruiter
     * @return Recruiter sesuai dengan parameter, jika tidak ditemukan maka return null
     */
    @RequestMapping("/{id}")
    public Recruiter getRecruiterById(@PathVariable int id) {
        try {
            return DatabaseRecruiterPostgre.getRecruiter(id);
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    /** menambah Recruiter ke database
     * @param name nama Recruiter
     * @param email email Recruiter
     * @param phoneNumber nomor telepon Recruiter
     * @param province lokasi provinsi Recruiter
     * @param city lokasi kota Recruiter
     * @param description deskripsi lokasi Recruiter
     * @return Recruiter yang terdaftar, jika melanggar aturan dbms maka return null
     */
    @RequestMapping(value="/", method = RequestMethod.POST)
    public Recruiter addRecruiter(  @RequestParam(value="name") String name,
                                    @RequestParam(value="email") String email,
                                    @RequestParam(value="phoneNumber") String phoneNumber,
                                    @RequestParam(value="province") String province,
                                    @RequestParam(value="city") String city,
                                    @RequestParam(value="description") String description)
    {
        try {
            int recruiterId = DatabaseRecruiterPostgre.insertRecruiter(name, email, phoneNumber, province, city, description);
            return new Recruiter(recruiterId, name, email, phoneNumber, new Location(province, city, description));
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }
}