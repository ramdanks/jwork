package RamadhanKalih.jwork;

/** kelas enumerasi menyimpan jenis pembayaran untuk mengidentifikasi
 * subclass dari class @Invoice yaitu: @BankPayment dan @EwalletPayment
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210325
 */
public enum PaymentType
{
    BankPayment("BankPayment"),
    EwalletPayment("EwalletPayment");
    
    /** representasi enum berupa string */
    private String name;
    
    /** ctor insiasi nama enum dengan string */
    private PaymentType(String name) {
        this.name = name;
    }
    
    /** mengakses enumerasi dalam bentuk String */
    public String toString() {
        return name;
    }

    /** mengubah bentuk string menjadi enum */
    public static PaymentType fromString(String text) {
        for (PaymentType cat : PaymentType.values())
            if (cat.name.equalsIgnoreCase(text))
                return cat;
        return null;
    }
}
