package RamadhanKalih.jwork;
import java.util.ArrayList;

public class FeeCalculator implements Runnable
{
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
