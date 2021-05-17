package RamadhanKalih.jwork;
import java.util.ArrayList;

/** Kelas threading untuk menjalankan program secara asinkron
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210517
 */
public class FeeCalculator implements Runnable
{
    /** menghitung totalfee setiap invoice dalam database */
    public void run() {
        ArrayList<Invoice> list = DatabaseInvoice.getInvoiceDatabase();
        for (Invoice i : list)
        {
            System.out.println("calculating invoice id: " + i.getId());
            i.setTotalFee();
            System.out.println("finish calculating invoice id: " + i.getId());
        }
    }
}
