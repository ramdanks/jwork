/**
 * Kelas yang menyimpan seluruh database recruiter
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210325
 */

public class DatabaseRecruiter
{
    /** menyimpan list Recruiter dari namanya */
    private static String[] listRecruiter;
    
    /**
     * menambah entri Recruiter kedalam database
     * @param Recruiter: Recruiter
     * @return boolean:
     */
    public static boolean addRecruiter(Recruiter recruiter) {
        System.out.println("Recruiter ditambahkan ke Database");
        return false;
    }
    /**
     * menghapus entri Recruiter kedalam database
     * @param Recruiter: Recruiter
     * @return boolean:
     */
    public static boolean removeRecruiter(Recruiter recruiter) {
        return false;
    }
    /**
     * meminta Recruiter dari database
     * @return Recruiter: Recruiter
     */
    public static Recruiter getRecruiter() {
        return null;
    }
    /**
     * meminta seluruh Recruiter yang tercatat dalam database
     * @return String[]: list Recruiter
     */
    public static String[] getListRecruiter() {
        return listRecruiter;
    }
}
