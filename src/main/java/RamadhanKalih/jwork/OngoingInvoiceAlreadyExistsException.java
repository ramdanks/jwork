package RamadhanKalih.jwork;

/** Kelas exception untuk invoice berstatus OnGoing sudah terdaftar
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210517
 */
public class OngoingInvoiceAlreadyExistsException extends Exception
{
    /** Invoice yang bermasalah */
    private Invoice invoice_error;

    /** ctor inisiasi variabel */
    public OngoingInvoiceAlreadyExistsException(Invoice invoice_input) {
        super("Ongoing Invoice: ");
        invoice_error = invoice_input;
    }
    
    /** mengembalikan keterangan invoice yang sudah terdaftar */
    public String getMessage() {
        return super.getMessage() + invoice_error.getId() + " already exists";
    }
}
