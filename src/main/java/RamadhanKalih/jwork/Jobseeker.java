package RamadhanKalih.jwork;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/** Kelas untuk menyimpan data pencari kerja
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210325
 */
public class Jobseeker
{
    /** nomor unik dari jobseeker */
    private int id;
    /** nama jobseeker dalam bentuk string */
    private String name;
    /** email jobseeker dalam bentuk string */
    private String email;
    /** password jobseeker dalam bentuk string */
    private String password;
    /** waktu jobseeker bergabung atau dibangun*/
    private Calendar joinDate;
    
    private static final String EMAIL_PATTERN = "^(?!.*([.])\\1)[^.-][a-zA-Z0-9.&*_~]+@[^-. ][a-zA-Z0-9-.&*_~]+(?:\\.[a-zA-Z0-9-]+)*";
    private static final String PASSWORD_PATTERN = "^(?=.{6,}$)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).*$";

    /** ctor untuk inisialisasi variable dengan tanggal bergabung*/
    public Jobseeker(int id, String name, String email,
                    String password, Calendar joinDate) {
        this.id = id;
        this.name = name;
        setEmail(email);
        setPassword(password);
        this.joinDate = joinDate;
    }
    
    /** ctor untuk inisialisasi variable dengan tanggal bergabung*/
    public Jobseeker(int id, String name, String email,
                    String password, int year, int month,
                    int dayOfMonth) {
        this.id = id;
        this.name = name;
        setEmail(email);
        setPassword(password);
        setJoinDate(year, month, dayOfMonth);
    }

    /** ctor untuk inisialisasi variable tanpa tanggal bergabung */
    public Jobseeker(int id, String name, String email,
                    String password) {
        this.id = id;
        this.name = name;
        setEmail(email);
        setPassword(password);
        this.joinDate = null;
    }

    /** akses nomor id entri jobseeker
     * @return nomor id entri jobseeker
     */
    public int getID() {
        return id;
    }

    /** akses nama jobseeker
     * @return nama jobseeker
     */
    public String getName() {
        return name;
    }

    /** akses email jobseeker
     * @return email jobseeker
     */
    public String getEmail() {
        return email;
    }

    /** akses password jobseeker
     * @return password jobseeker
     */
    public String getPassword() {
        return password;
    }

    /** akses tanggal bergabung
     * @return tanggal bergabung
     */
    public Calendar getJoinDate() {
        return joinDate;
    }

    /** mutasi id jobseeker
     * @param id nomor id yang baru
     */
    public void setID(int id) {
        this.id = id;
    }

    /** mutasi nama jobseeker
     * @param name nama yang baru
     */
    public void setName(String name) {
        this.name = name;
    }

    /** mutasi email jobseeker, akan disanitasi dengan regex
     * jika tidak memenuhi, variabel akan di set menjadi kosong
     * @param email email yang baru
     */
    public void setEmail(String email) {
        Pattern pt = Pattern.compile(EMAIL_PATTERN);
        Matcher mt = pt.matcher(email);
        if (mt.matches()) {
            this.email = email;
            return;
        }
        this.email = "";
    }

    /** mutasi email jobseeker, akan disanitasi dengan regex
     * jika tidak memenuhi, variabel akan di set menjadi kosong
     * @param password password yang baru
     */
    public void setPassword(String password) {
        Pattern pt = Pattern.compile(PASSWORD_PATTERN);
        Matcher mt = pt.matcher(password);
        if (mt.matches()) {
            this.password = password;
            return;
        }
        this.password = "";
    }

    /** mutasi waktu bergabung
     * @param joinDate waktu bergabung yang baru
     */
    public void setJoinDate(Calendar joinDate) {
        this.joinDate = joinDate;
    }

    /** mutasi waktu bergabung
     * @param year tahun bergabung
     * @param month bulan bergabung
     * @param dayOfMonth hari begabung
     */
    public void setJoinDate(int year, int month, int dayOfMonth) {
        this.joinDate = new GregorianCalendar(year, month-1, dayOfMonth);
    }

    /** mengakses atribut yang ada dalam kelas
     * @return String seluruh informasi pada kelas
     */
    public String toString() {
        String strDate = "";
        if (joinDate != null) {
            Date date = joinDate.getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            strDate = dateFormat.format(date);
        }
        return  "============ JOBSEEKER ============" +
                "\nId = " + id +
                "\nName = " + name +
                "\nEmail = " + email +
                "\nPassword = " + password +
                "\nJoin Date = " + strDate;
    }
}
