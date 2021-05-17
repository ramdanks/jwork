package RamadhanKalih.jwork;
import java.util.ArrayList;

/** Kelas yang menyimpan seluruh database recruiter
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210325
 */
public class DatabaseRecruiter
{
    /** menyimpan list Recruiter dari namanya */
    private static ArrayList<Recruiter> RECRUITER_DATABASE = new ArrayList<Recruiter>();
    private static int lastId = 0;

    /**
     * meminta seluruh Recruiter yang tercatat dalam database
     * @return ArrayList<Recruiter>
     */
    public static ArrayList<Recruiter> getRecruiterDatabase() {
        return RECRUITER_DATABASE;
    }
    public static int getLastId() {
        return lastId;
    }
    /**
     * meminta Recruiter dari database berdasarkan id
     * @param int id
     * @return Recruiter Recruiter
     */
    public static Recruiter getRecruiterById(int id) throws RecruiterNotFoundException {
        for (Recruiter r : RECRUITER_DATABASE)
            if (r.getId() == id)
                return r;
        throw new RecruiterNotFoundException(id);
    }
    /**
     * menambah entri Recruiter kedalam database
     * @param Recruiter: Recruiter
     * @return boolean:
     */
    public static boolean addRecruiter(Recruiter recruiter) {
        for (Recruiter r : RECRUITER_DATABASE)
            if (r.getId() == recruiter.getId())
                return false;
        RECRUITER_DATABASE.add(recruiter);
        lastId = recruiter.getId();
        return true;
    }
    /**
     * menghapus entri Recruiter kedalam database
     * @param int id milik Recruiter
     * @return boolean:
     */
    public static boolean removeRecruiter(int id) throws RecruiterNotFoundException {
        for (int i = 0; i < RECRUITER_DATABASE.size(); i++)
        {
            if (RECRUITER_DATABASE.get(i).getId() == id)
            {
                RECRUITER_DATABASE.remove(i);
                return true;
            }
        }
        throw new RecruiterNotFoundException(id);
    }

}
