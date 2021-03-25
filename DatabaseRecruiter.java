/**
 * Kelas yang menyimpan seluruh database recruiter
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210325
 */

public class DatabaseRecruiter
{
    private static String[] listRecruiter;
    
    public DatabaseRecruiter() {}
    public static boolean addRecruiter(Recruiter recruiter) {
        System.out.println("Recruiter ditambahkan ke Database");
        return false;
    }
    public static boolean removerRecruiter(Recruiter recruiter) {
        return false;
    }
    public static Recruiter getRecruiter() {
        return null;
    }
    public static String[] getListRecruiter() {
        return listRecruiter;
    }
}
