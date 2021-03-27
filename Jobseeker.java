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
    private String joinDate;
    
    public Jobseeker(int id, String name, String email,
                    String password, String joinDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.joinDate = joinDate;
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
    public String getJoinDate() {
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
        this.email = email;
    }
    /**
     * mutasi password jobseeker
     * @param int: menset password jobseeker
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * mutasi waktu bergabung
     * @param int: menset waktu bergabung jobseeker
     */
    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
    /**
     * mencetak nama jobseeker ke terminal
     */
    public void printData() {
        System.out.println(this.name);
    }
}
