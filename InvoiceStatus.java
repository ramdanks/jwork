/**
 * Kelas untuk menyimpan status Invoice
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210327
 */

public enum InvoiceStatus {
    
    OnGoing("OnGoing"),
    Finished("Finished"),
    Cancelled("Cancelled");

    private String name;
    
    private InvoiceStatus(String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
