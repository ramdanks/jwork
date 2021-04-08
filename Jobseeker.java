import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Kelas untuk menyimpan data pencari kerja
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210325
 */

public class Jobseeker
{
    private int id;
    private String name;
    private String email;
    private String password;
    private Calendar joinDate;
    
    /** ctor untuk inisialisasi variable */
    public Jobseeker(int id, String name, String email,
                    String password, Calendar joinDate) {
        this.id = id;
        this.name = name;
        this.setEmail(email);
        this.setPassword(password);
        this.joinDate = joinDate;
    }
    /** ctor untuk inisialisasi variable */
    public Jobseeker(int id, String name, String email,
                    String password, int year, int month,
                    int dayOfMonth) {
        this.id = id;
        this.name = name;
        this.setEmail(email);
        this.setPassword(password);
        this.setJoinDate(year, month, dayOfMonth);
    }
    /** ctor untuk inisialisasi variable */
    public Jobseeker(int id, String name, String email,
                    String password) {
        this.id = id;
        this.name = name;
        this.setEmail(email);
        this.setPassword(password);
        this.joinDate = new GregorianCalendar();
    }
    /**
     * akses nomor id entri jobseeker
     * @return int: nomor id entri jobseeker
     */
    public int getID() {
        return id;
    }
    /**
     * akses nama jobseeker
     * @return String: nama jobseeker
     */
    public String getName() {
        return name;
    }
    /**
     * akses email jobseeker
     * @return String: email jobseeker
     */
    public String getEmail() {
        return email;
    }
    /**
     * akses password jobseeker
     * @return String: password jobseeker
     */
    public String getPassword() {
        return password;
    }
    /**
     * akses tanggal bergabung
     * @return String: tanggal bergabung
     */
    public Calendar getJoinDate() {
        return joinDate;
    }
    /**
     * mutasi id jobseeker
     * @param int: menset id jobseeker
     */
    public void setID(int id) {
        this.id = id;
    }
    /**
     * mutasi nama jobseeker
     * @param int: menset id jobseeker
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * mutasi email jobseeker
     * @param int: menset id jobseeker
     */
    public void setEmail(String email) {
        Pattern pt = Pattern.compile("[^\\.\\W].*@[\\w]{1}[-\\w\\S].*$");
        Matcher mt = pt.matcher(email);
        if (mt.matches()) {
            this.email = email;
            return;
        }
        this.email = "";
    }
    /**
     * mutasi password jobseeker
     * @param int: menset password jobseeker
     */
    public void setPassword(String password) {
        Pattern pt = Pattern.compile("^(?=.{6,}$)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).*$");
        Matcher mt = pt.matcher(password);
        if (mt.matches()) {
            this.password = password;
            return;
        }
        this.password = "";
    }
    /**
     * mutasi waktu bergabung
     * @param int: menset waktu bergabung jobseeker
     */
    public void setJoinDate(Calendar joinDate) {
        this.joinDate = joinDate;
    }
    public void setJoinDate(int year, int month, int dayOfMonth) {
        this.joinDate = new GregorianCalendar(year, month, dayOfMonth);
    }
    public String toString() {
        Date date = joinDate.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = dateFormat.format(date);
        return  "============ JOBSEEKER ============" +
                "\nId = " + id +
                "\nName = " + name +
                "\nEmail = " + email +
                "\nPassword = " + password +
                "\nJoin Date = " + strDate;
    }
}
