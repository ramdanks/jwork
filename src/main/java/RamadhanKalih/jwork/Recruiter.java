package RamadhanKalih.jwork;

/** Kelas untuk menyimpan informasi perekrut kerja
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210325
 */
public class Recruiter
{
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private Location location;
    
    /** ctor untuk insialisasi variable */
    public Recruiter(int id, String name, String email,
                    String phoneNumber, Location location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }
    /**
     * akses nomor id perekrut
     * @return int: nomor id perekrut
     */
    public int getId() {
        return id;
    }
    /**
     * akses nama perekrut
     * @return String: nama perekrut
     */
    public String getName() {
        return name;
    }
    /**
     * akses email perekrut
     * @return String: email perekrut
     */
    public String getEmail() {
        return email;
    }
    /**
     * akses nomor telepon perekrut
     * @return String: nomor telepon perekrut
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * akses lokasi perekrut
     * @return Location: lokasi perekrut
     */
    public Location getLocation() {
        return location;
    }
    /**
     * mutasi id perekrut
     * @param int: id perekrut
     */
    public void setID(int id) {
        this.id = id;
    }
    /**
     * mutasi email perekrut
     * @param String: email perekrut
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * mutasi nama perekrut
     * @param String: nama perekrut
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * mutasi nomor telepon perekrut
     * @param String: nomor telepon perekrut
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * mutasi lokasi perekrut
     * @param Location: lokasi perekrut
     */
    public void setLocation(Location location) {
        this.location = location;
    }
    /**
     * mengakses atribut yang ada dalam kelas
     * @return String seluruh informasi pada kelas
     */
    public String toString() {
        return  "============ RECRUITER ============" +
                "\nId = " + id +
                "\nName = " + name +
                "\nPhoneNumber = " + phoneNumber +
                "\nLocation = " + location.getCity();
    }
}
