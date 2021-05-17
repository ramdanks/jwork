/**
 * Kelas untuk menyimpan informasi lokasi
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210325
 */

package RamadhanKalih.jwork;

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
    /**
     * mengakses atribut yang ada dalam kelas
     * @return String seluruh informasi pada kelas
     */
    public String toString() {
        return  "============ LOCATION ============" +
                "\nProvince = " + province +
                "\nCity = " + city +
                "\nDescription = " + description;
    }
}
