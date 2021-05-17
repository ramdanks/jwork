/**
 * Kelas untuk menyimpan status Invoice
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210327
 */

package RamadhanKalih.jwork;

public enum InvoiceStatus {
    
    OnGoing("OnGoing"),
    Finished("Finished"),
    Cancelled("Cancelled");

    /** representasi enum berupa string */
    private String name;
    
    /** ctor insiasi nama enum dengan string */
    private InvoiceStatus(String name) {
        this.name = name;
    }
    /** mengakses enumerasi dalam bentuk String */
    public String toString() {
        return name;
    }
}
