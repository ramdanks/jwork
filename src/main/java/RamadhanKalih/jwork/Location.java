package RamadhanKalih.jwork;

/** Kelas untuk menyimpan informasi lokasi
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210325
 */
public class Location
{
    /** nama provinsi dalam bentuk string */
    private String province;
    /** nama kota dalam bentuk string */
    private String city;
    /** deskripsi lokasi dalam bentuk string  */
    private String description;
    
    /** ctor untuk inisialisasi variable */
    public Location(String province, String city, String description) {
        this.province = province;
        this.city = city;
        this.description = description;
    }
    
    /** akses lokasi provinsi
     * @return lokasi provinsi
     */
    public String getProvince() {
        return province;
    }

    /** akses lokasi kota
     * @return lokasi kota
     */
    public String getCity() {
        return city;
    }

    /** akses keterangan lokasi
     * @return keterangan lokasi
     */
    public String getDescription() {
        return description;
    }

    /** mutasi lokasi provinsi
     * @param province provinsi yang baru
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /** mutasi lokasi kota
     * @param city kota yang baru
     */
    public void setCity(String city) {
        this.city = city;
    }

    /** mutasi deskripsi lokasi
     * @param description deskripsi yang baru
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /** mengakses atribut yang ada dalam kelas
     * @return String seluruh informasi pada kelas
     */
    public String toString() {
        return  "============ LOCATION ============" +
                "\nProvince = " + province +
                "\nCity = " + city +
                "\nDescription = " + description;
    }
}
