
/**
 * Enumeration class PaymentType - write a description of the enum class here
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210325
 */

public enum PaymentType
{
    BankPayment("Bank Payment"),
    EwalletPayment("E-Wallet Payment");
    
    private String name;
    
    private PaymentType(String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
