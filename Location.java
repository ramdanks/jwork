/**
* @Author: Ramadhan Kalih Sewu (1806148826)
* @Version: 210318
* 
* Kelas untuk menyimpan informasi lokasi
*/

public class Location
{
    private String province;
    private String city;
    private String description;
    
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
        return this.province;
    }
    /**
     * akses lokasi kota
     * @return String: lokasi kota
     */
    public String getCity() {
        return this.city;
    }
    /**
     * akses keterangan lokasi
     * @return String: keterangan lokasi
     */
    public String getDescription() {
        return this.description;
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
     * mencetak lokasi provinsi ke terminal
     */
    public void printData() {
        System.out.println(this.province);
    }

}
