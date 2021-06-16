package RamadhanKalih.jwork;
import java.util.ArrayList;

/** Kelas yang menyimpan list Jobseeker (pencari pekerjaan)
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210325
 */
public class DatabaseJobseeker
{
    /** menyimpan list Jobseeker */
    private static ArrayList<Jobseeker> JOBSEEKER_DATABASE = new ArrayList<Jobseeker>();
    /** menyimpan nomor id Jobseeker yang terakhir diperbarui ke local arraylist */
    private static int lastId = 0;

    /** meminta seluruh Jobseeker yang tercatat dalam local cache
     * @return ArrayList<Jobseeker>
     */
    public static ArrayList<Jobseeker> getDatabaseJobseeker() {
        return JOBSEEKER_DATABASE;
    }

    /** meminta nomor id jobseeker yang berhasil diperbarui kedalam local arraylist
     * @return int nomor id Jobseeker
     */
    public static int getLastId() {
        return lastId;
    }

    /** meminta jobseeker dalam database
     * @param id nomor id Jobseeker yang diminta
     * @return Jobseeker yang sesuai dengan parameter
     * @throws JobseekerNotFoundException tidak menemukan Jobseeker sesuai dengan parameter
     */
    public static Jobseeker getJobseekerById(int id) throws JobseekerNotFoundException {
        // get from local cache
        for (Jobseeker js : JOBSEEKER_DATABASE)
            if (js.getID() == id)
                return js;
        // throw exception if not found anywhere
        throw new JobseekerNotFoundException(id);
    }
    /** menambah entri Jobseeker kedalam database
     * @param jobseeker Jobseeker yang ingin dimasukkan
     * @return false jika duplikat id atau gagal memperbarui postgreSQL
     * @throws EmailAlreadyExistsException jobseeker harus memiliki email unik (tidak sama dgn lainnya)
     */
    public static boolean addJobseeker(Jobseeker jobseeker) throws EmailAlreadyExistsException {
        // sanity check from local array
        for (Jobseeker js : JOBSEEKER_DATABASE)
        {
            if (js.getID() == jobseeker.getID())
                return false;
            if (js.getEmail().equals(jobseeker.getEmail()))
                throw new EmailAlreadyExistsException(jobseeker);
        }
        // insert into database
        JOBSEEKER_DATABASE.add(jobseeker);
        lastId = jobseeker.getID();
        return true;
    }
    /** menghapus entri Jobseeker dari database
     * @param id nomor id Jobseeker yang ingin dihapus
     * @return true jika berhasil, false apabila error dalam proses ke dbms
     * @throws JobseekerNotFoundException tidak menemukan jobseeker sesuai dengan parameter
     */
    public static boolean removeJobseeker(int id) throws JobseekerNotFoundException {
        for (int i = 0; i < JOBSEEKER_DATABASE.size(); i++)
        {
            if (JOBSEEKER_DATABASE.get(i).getID() == id)
            {
                JOBSEEKER_DATABASE.remove(i);
                return true;
            }
        }
        throw new JobseekerNotFoundException(id);
    }

    public static Jobseeker getJobseekerLogin(String email, String password) {
        // check in local cache
        for (Jobseeker js : JOBSEEKER_DATABASE)
        {
            if (js.getEmail().equals(email) &&
                js.getPassword().equals(password))
                return js;
        }
        return null;
    }
}