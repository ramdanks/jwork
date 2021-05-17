/**
 * Kelas yang menyimpan list pekerjaan yang tersedia
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210424
 */

package RamadhanKalih.jwork;
import java.util.ArrayList;

public class DatabaseInvoice
{
    private static ArrayList<Invoice> INVOICE_DATABASE = new ArrayList<Invoice>();
    private static int lastId = 0;

    public static ArrayList<Invoice> getInvoiceDatabase() {
        return INVOICE_DATABASE;
    }
    public static int getLastId() {
        return lastId;
    }
    public static Invoice getInvoiceById(int id) throws InvoiceNotFoundException {
        for (Invoice i : INVOICE_DATABASE)
            if (i.getId() == id)
                return i;
        throw new InvoiceNotFoundException(id);
    }
    public static ArrayList<Invoice> getInvoiceByJobseeker(int jobseekerId) {
        ArrayList<Invoice> list = new ArrayList<Invoice>();
        for (Invoice i : INVOICE_DATABASE)
            if (i.getJobseeker().getID() == jobseekerId)
                list.add(i);
        if (list.isEmpty())
            return null;
        return list;
    }
    public static boolean addInvoice(Invoice invoice) throws OngoingInvoiceAlreadyExistsException {
        // tidak menerima invoice yang masih berstatus ongoing
        if (invoice.getInvoiceStatus() == InvoiceStatus.OnGoing)
            throw new OngoingInvoiceAlreadyExistsException(invoice);
        // tidak menerima invoice dengan id yang sama
        for (Invoice i : INVOICE_DATABASE)
            if (i.getId() == invoice.getId())
                throw new OngoingInvoiceAlreadyExistsException(invoice);
        // masukkan ke db jika kondisi diatas memenuhi
        INVOICE_DATABASE.add(invoice);
        lastId = invoice.getId();
        return true;
    }
    public static boolean changeInvoiceStatus(int id, InvoiceStatus invoiceStatus) {
        for (Invoice invoice : INVOICE_DATABASE)
        {
            if(invoice.getId() == id && invoice.getInvoiceStatus() == InvoiceStatus.OnGoing)
            {
                invoice.setInvoiceStatus(invoiceStatus);
                return true;
            }
        }
        return false;
    }
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
