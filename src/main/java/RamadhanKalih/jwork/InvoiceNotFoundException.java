package RamadhanKalih.jwork;

/** Kelas exception untuk Invoice yang tidak ditemukan
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210517
 */
public class InvoiceNotFoundException extends Exception
{
    /** id invoice yang bermasalah */
    private int invoice_error;

    /** ctor inisiasi variabel */
    public InvoiceNotFoundException(int invoice_input) {
        super("Invoice ID: ");
        invoice_error = invoice_input;
    }
    /** mengembalikan keterangan invoice yang tidak ditemukan */
    public String getMessage() {
        return super.getMessage() + invoice_error + " not found";
    }
}
