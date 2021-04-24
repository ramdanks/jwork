import java.util.ArrayList;

/**
 * Kelas yang menyimpan list pekerjaan yang tersedia
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210424
 */

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
    public static Invoice getInvoiceById(int id) {
        for (Invoice i : INVOICE_DATABASE)
            if (i.getId() == id)
                return i;
        return null;
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
    public static boolean addInvoice(Invoice invoice) {
        if (invoice.getInvoiceStatus() == InvoiceStatus.OnGoing)
            return false;
        for (Invoice i : INVOICE_DATABASE)
            if (i.getId() == invoice.getId())
                return false;
        INVOICE_DATABASE.add(invoice);
        lastId = invoice.getId();
        return true;
    }
    public static boolean changeInvoiceStatus(int id, InvoiceStatus invoiceStatus) {
        for (int i = 0; i < INVOICE_DATABASE.size(); i++)
        {
            if (INVOICE_DATABASE.get(i).getId() == id)
            {
                INVOICE_DATABASE.get(i).setInvoiceStatus(invoiceStatus);
                return true;
            }
        }
        return false;
    }
    public static boolean removeInvoice(int id) {
        for (int i = 0; i < INVOICE_DATABASE.size(); i++)
        {
            if (INVOICE_DATABASE.get(i).getId() == id)
            {
                INVOICE_DATABASE.remove(i);
                return true;
            }
        }
        return false;
    }
}
