/**
 * Kelas untuk menyimpan informasi lokasi
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210325
 */

public class Location
{
    private String province;
    private String city;
    private String description;
    
    /** ctor untuk inisialisasi variable */
    public Location(String province, String city, String description) {
        this.province = province;
        this.city = city;
        this.description = description;
    }
    /**
     * akses lokasi provinsi
     * @return String: lokasi provinsi
     */
    public String getProvince() {
        return province;
    }
    /**
     * akses lokasi kota
     * @return String: lokasi kota
     */
    public String getCity() {
        return city;
    }
    /**
     * akses keterangan lokasi
     * @return String: keterangan lokasi
     */
    public String getDescription() {
        return description;
    }
    /**
     * mutasi lokasi provinsi
     * @param String: menset lokasi provinsi
     */
    public void setProvince(String province) {
        this.province = province;
    }
    /**
     * mutasi lokasi kota
     * @param String: menset lokasi kota
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * mutasi deskripsi lokasi
     * @param String: menset deskripsi lokasi
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /** mencetak lokasi provinsi ke terminal */
    public void printData() {
        System.out.println(this.province);
    }

}
