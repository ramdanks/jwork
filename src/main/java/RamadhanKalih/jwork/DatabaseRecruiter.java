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
    /** id yang terakhir diperbarui ke database */
    private static int lastId = 0;

    /** meminta seluruh Recruiter yang tercatat dalam database
     * @return ArrayList<Recruiter>
     */
    public static ArrayList<Recruiter> getRecruiterDatabase() {
        return RECRUITER_DATABASE;
    }

    /** meminta id terakhir yang dimasukkan ke database melalui metode addRecruiter */
    public static int getLastId() {
        return lastId;
    }

    /** meminta Recruiter dari database berdasarkan id
     * @param id id recruiter yang dicari dalam database
     * @return Recruiter sesuai id parameter
     * @throws RecruiterNotFoundException jika tidak ditemukan
     */
    public static Recruiter getRecruiterById(int id) throws RecruiterNotFoundException {
        for (Recruiter r : RECRUITER_DATABASE)
            if (r.getId() == id)
                return r;
        throw new RecruiterNotFoundException(id);
    }

    /** menambah entri Recruiter kedalam database
     * @param recruiter recruiter yang dimasukkan
     * @return true jika berhasil, false jika duplikat id
     */
    public static boolean addRecruiter(Recruiter recruiter) {
        for (Recruiter r : RECRUITER_DATABASE)
            if (r.getId() == recruiter.getId())
                return false;
        RECRUITER_DATABASE.add(recruiter);
        lastId = recruiter.getId();
        return true;
    }

    /** menghapus entri Recruiter kedalam database
     * @param id milik Recruiter
     * @return true jika berhasil menghapus
     * @throws RecruiterNotFoundException jika tidak menemukan
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
