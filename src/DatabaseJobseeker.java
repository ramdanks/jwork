import java.util.ArrayList;

/**
 * Kelas yang menyimpan list Jobseeker (pencari pekerjaan)
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210325
 */

public class DatabaseJobseeker
{
    /** menyimpan list Jobseeker dari namanya */
    private static ArrayList<Jobseeker> JOBSEEKER_DATABASE = new ArrayList<Jobseeker>();
    private static int lastId = 0;

    /**
     * meminta seluruh Jobseeker yang tercatat dalam database
     * @return ArrayList<Jobseeker>
     */
    public static ArrayList<Jobseeker> getDatabaseJobseeker() {
        return JOBSEEKER_DATABASE;
    }
    public static int getLastId() {
        return lastId;
    }
    public static Jobseeker getJobseekerById(int id) throws JobseekerNotFoundException {
        for (Jobseeker js : JOBSEEKER_DATABASE)
            if (js.getID() == id)
                return js;
        throw new JobseekerNotFoundException(id);
    }
    /**
     * menambah entri Jobseeker kedalam database
     * @param Jobseeker: Jobseeker
     * @return boolean:
     */
    public static boolean addJobseeker(Jobseeker jobseeker) throws EmailAlreadyExistsException {
        for (Jobseeker js : JOBSEEKER_DATABASE)
        {
            if (js.getID() == jobseeker.getID())
                return false;
            if (js.getEmail() == jobseeker.getEmail())
                throw new EmailAlreadyExistsException(jobseeker);
        }
        JOBSEEKER_DATABASE.add(jobseeker);
        lastId = jobseeker.getID();
        return true;
    }
    /**
     * menghapus entri Jobseeker dari database
     * @param Jobseeker: Jobseeker
     * @return boolean:
     */
    public static boolean removeJobseeker(int id) throws JobseekerNotFoundException {
        for (int i = 0; i < JOBSEEKER_DATABASE.size(); i++)
        {
            if (JOBSEEKER_DATABASE.get(i).getID() == id)
            {
                JOBSEEKER_DATABASE.remove(id);
                return true;
            }
        }
        throw new JobseekerNotFoundException(id);
    }
}