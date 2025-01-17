package RamadhanKalih.jwork;

/** Kelas untuk menyimpan status Invoice
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210327
 */
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
    
    /** mengubah bentuk string menjadi enum */
    public static InvoiceStatus fromString(String text) {
        for (InvoiceStatus cat : InvoiceStatus.values())
            if (cat.name.equalsIgnoreCase(text))
                return cat;
        return null;
    }
}
