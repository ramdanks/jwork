package RamadhanKalih.jwork;

/** Kelas untuk menyimpan informasi perekrut kerja
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210325
 */
public class Recruiter
{
    /** nomor unik recruiter */
    private int id;
    /** nama recruiter */
    private String name;
    /** email recruiter */
    private String email;
    /** nomor telepon recruiter */
    private String phoneNumber;
    /** lokasi recruiter */
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

    /** akses nomor id perekrut
     * @return nomor id perekrut
     */
    public int getId() {
        return id;
    }

    /** akses nama perekrut
     * @return nama perekrut
     */
    public String getName() {
        return name;
    }

    /** akses email perekrut
     * @return email perekrut
     */
    public String getEmail() {
        return email;
    }

    /** akses nomor telepon perekrut
     * @return nomor telepon perekrut
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /** akses lokasi perekrut
     * @return lokasi perekrut
     */
    public Location getLocation() {
        return location;
    }

    /** mutasi id perekrut
     * @param id id yang baru
     */
    public void setID(int id) {
        this.id = id;
    }

    /** mutasi email perekrut
     * @param email email yang baru
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /** mutasi nama perekrut
     * @param nama nama yang baru
     */
    public void setName(String name) {
        this.name = name;
    }

    /** mutasi nomor telepon perekrut
     * @param phoneNumber nomor telepon yang baru
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /** mutasi lokasi perekrut
     * @param location lokasi yang baru
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /** mengakses atribut yang ada dalam kelas
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
