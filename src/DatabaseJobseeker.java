/**
 * Kelas yang menyimpan list Jobseeker (pencari pekerjaan)
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210325
 */

public class DatabaseJobseeker
{
    /** menyimpan list Jobseeker dari namanya */
    private static String[] listJobseeker;
    
    /**
     * menambah entri Jobseeker kedalam database
     * @param Jobseeker: Jobseeker
     * @return boolean:
     */
    public static boolean addJobseeker(Jobseeker jobseeker) {
        return false;
    }
    /**
     * menghapus entri Jobseeker dari database
     * @param Jobseeker: Jobseeker
     * @return boolean:
     */
    public static boolean removeJobseeker(Jobseeker jobseeker) {
        return false;
    }
    /**
     * meminta Jobseeker dari database
     * @return Jobseeker: Jobseeker
     */
    public static Jobseeker getJobseeker() {
        return null;
    }
    /**
     * meminta seluruh Jobseeker yang tercatat dalam database
     * @return String[]: list Jobseeker
     */
    public static String[] getListJobseeker() {
        return listJobseeker;
    }
}