
/**
 * Menyimpan seluruh database recruiter
 *
 * @author Ramadhan Kalih Sewu
 * @version 210320
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
        return DatabaseRecruiter.listRecruiter;
    }
}
