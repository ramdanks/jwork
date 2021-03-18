/**
* @Author: Ramadhan Kalih Sewu (1806148826)
* @Version: 210318
* 
* Kelas untuk menyimpan informasi perekrut kerja
*/

public class Recruiter
{
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private Location location;
    
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
    public int getID() {
        return this.id;
    }
    /**
     * akses nama perekrut
     * @return String: nama perekrut
     */
    public String getName() {
        return this.name;
    }
    /**
     * akses email perekrut
     * @return String: email perekrut
     */
    public String getEmail() {
        return this.email;
    }
    /**
     * akses nomor telepon perekrut
     * @return String: nomor telepon perekrut
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    /**
     * akses lokasi perekrut
     * @return Location: lokasi perekrut
     */
    public Location getLocation() {
        return this.location;
    }
    /**
     * mutasi id perekrut
     * @param int: menset id perekrut
     */
    public void setID(int id) {
        this.id = id;
    }
    /**
     * mutasi email perekrut
     * @param String: menset email perekrut
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * mutasi nama perekrut
     * @param String: menset nama perekrut
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @param String: menset nomor telepon perekrut
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * mutasi lokasi perekrut
     * @param Location: menset lokasi perekrut
     */
    public void setLocation(Location location) {
        this.location = location;
    }
    /**
     * mencetak nama perekrut ke terminal
     */
    public void printData() {
        System.out.println(this.name);
    }
}
