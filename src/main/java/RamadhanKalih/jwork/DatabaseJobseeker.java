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
        // if not found, get from dbms
        try {
            return DatabaseJobseekerPostgre.getJobseeker(id);
        } catch (Exception e) {
            System.err.println(e);
        }
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
        // insert into postgre
        try {
            boolean success = DatabaseJobseekerPostgre.insertJobseeker(jobseeker);
            if (!success) throw new Exception("Unknown PSQL fail!");
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
        // update table as a cache
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
        boolean found = false;
        // Remove from postgreSQL
        try {
            found = DatabaseJobseekerPostgre.removeJobseeker(id);
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
        // throw exception if not found in dbms
        if (!found) throw new JobseekerNotFoundException(id);
        // Remove from cache as well
        for (int i = 0; i < JOBSEEKER_DATABASE.size(); i++)
        {
            if (JOBSEEKER_DATABASE.get(i).getID() == id)
            {
                JOBSEEKER_DATABASE.remove(i);
                break;
            }
        }
        return true;
    }
    public static Jobseeker getJobseekerLogin(String email, String password) {
        // check in local cache
        for (Jobseeker js : JOBSEEKER_DATABASE)
        {
            if (js.getEmail().equals(email) &&
                js.getPassword().equals(password))
                return js;
        }
        // if not found, check from dbms
        Jobseeker js = null;
        try {
            js = DatabaseJobseekerPostgre.getJobseeker(email, password);
            if (js != null)
            {
                // update to cache
                JOBSEEKER_DATABASE.add(js);
                lastId = js.getID();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return js;
    }
}