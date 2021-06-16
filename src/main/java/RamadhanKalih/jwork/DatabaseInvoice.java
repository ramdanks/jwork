package RamadhanKalih.jwork;
import java.util.ArrayList;

/** Kelas yang menyimpan list pekerjaan yang tersedia
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210424
 */
public class DatabaseInvoice
{
    /** menyimpan list Invoice */
    private static ArrayList<Invoice> INVOICE_DATABASE = new ArrayList<Invoice>();
    /** menyimpan nomor id Bonus yang terakhir berhasil masuk ke database melalui method addInvoice() */
    private static int lastId = 0;

    /** meminta database invoice
     * @return ArrayList<Invoice> list database Invoice tersimpan dalam kelas ini
     */
    public static ArrayList<Invoice> getInvoiceDatabase() {
        return INVOICE_DATABASE;
    }

    /** meminta nomor id Bonus yang terakhir masuk ke database melalui method addInvoice()
     * @return nomor id Invoice
     */
    public static int getLastId() {
        return lastId;
    }

    /** meminta invoice dalam database kelas ini
     * @param id nomor id Invoice
     * @return Invoice dengan id sesuai parameter
     * @throws InvoiceNotFoundException jika tidak ditemukan invoice yang dimaksud
     */
    public static Invoice getInvoiceById(int id) throws InvoiceNotFoundException {
        for (Invoice i : INVOICE_DATABASE)
            if (i.getId() == id)
                return i;
        throw new InvoiceNotFoundException(id);
    }

    /** meminta Invoice dalam database kelas ini
     * @param jobseekerId nomor id Jobseeker
     * @return ArrayList<Invoice> list Invoice yang terafiliasi dengan parameter
     * @return null jika tidak ditemukan Invoice yang terafiliasi dengan parameter
     */
    public static ArrayList<Invoice> getInvoiceByJobseeker(int jobseekerId) {
        ArrayList<Invoice> list = new ArrayList<Invoice>();
        for (Invoice i : INVOICE_DATABASE)
            if (i.getJobseeker().getID() == jobseekerId)
                list.add(i);
        if (list.isEmpty())
            return null;
        return list;
    }

    /** memasukkan Invoice ke database kelas ini
     * @param invoice Invoice yang ingin dimasukkan
     * @return boolean true jika berhasil memasukkan invoice ke database
     * @throws OngoingInvoiceAlreadyExistsException apabila status invoice OnGoing atau id yang sama sudah ada dalam database
     */
    public static boolean addInvoice(Invoice invoice) throws OngoingInvoiceAlreadyExistsException {
        // tidak boleh ada invoice dengan id yang sama dalam database
        for (Invoice i : INVOICE_DATABASE)
            if (i.getId() == invoice.getId())
                throw new OngoingInvoiceAlreadyExistsException(invoice);
        // masukkan ke db jika kondisi diatas memenuhi
        INVOICE_DATABASE.add(invoice);
        lastId = invoice.getId();
        return true;
    }

    /** mengubah status invoice dalam database kelas ini dengan syarat
     * status invoice tersebut ongoing
     * @param id nomor id Invoice yang ingin diubah
     * @param invoiceStatus status yang ingin di set
     * @return boolean true jika berhasil, false jika tidak
     */
    public static boolean changeInvoiceStatus(int id, InvoiceStatus invoiceStatus) {
        for (Invoice invoice : INVOICE_DATABASE)
        {
            if (invoice.getId() == id && invoice.getInvoiceStatus() == InvoiceStatus.OnGoing)
            {
                invoice.setInvoiceStatus(invoiceStatus);
                return true;
            }
        }
        return false;
    }

    /** menghapus Invoice dari database kelas ini
     * @param id nomor id Invoice yang dihapus
     * @return true jika berhasil
     * @throws InvoiceNotFoundException apabila tidak menemukan Invoice yang diminta parameter
     */
    public static boolean removeInvoice(int id) throws InvoiceNotFoundException {
        for (int i = 0; i < INVOICE_DATABASE.size(); i++)
        {
            if (INVOICE_DATABASE.get(i).getId() == id)
            {
                INVOICE_DATABASE.remove(i);
                return true;
            }
        }
        throw new InvoiceNotFoundException(id);
    }
}
