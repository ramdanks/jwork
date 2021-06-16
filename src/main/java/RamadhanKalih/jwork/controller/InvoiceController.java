package RamadhanKalih.jwork.controller;

import RamadhanKalih.jwork.*;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

/** Controller untuk menghandle REST objek Invoice dan memprosesnya ke dbms psql
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210617
 */
@RequestMapping("/invoice")
@RestController
public class InvoiceController
{
    /** meminta seluruh Invoice di database
     * @return Invoice dalam database, jika kosong maka return list kosong,
     * jika terdapat error koneksi atau saat mengurai data maka return null
     */
    @RequestMapping(value="", method = RequestMethod.GET)
    public ArrayList<Invoice> getAllInvoice() {
        try {
            return DatabaseInvoicePostgre.getAllInvoice();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }
        return null;
    }

    /** meminta Invoice di database dengan id
     * @param id nomor id dari invoice
     * @return Invoice sesuai parameter, jika tidak ditemukan return null
     */
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Invoice getInvoiceById(@PathVariable int id) {
        try {
            return DatabaseInvoicePostgre.getInvoice(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }
        return null;
    }

    /** meminta Invoice di database berdasarkan jobseeker
     * @param jobseekerId nomor id dari jobseeker
     * @return list invoice dengan jobseeker sesuai parameter,
     * jika tidak ditemukan return empty list, jika error return null 
     */
    @RequestMapping(value="/jobseeker/{jobseekerId}", method = RequestMethod.GET)
    public ArrayList<Invoice> getInvoiceByJobseeker(@PathVariable int jobseekerId)
    {
        try {
            return DatabaseInvoicePostgre.getInvoiceByJobseeker(jobseekerId);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }
        return null;
    }

    /** mengubah status Invoice yang terdaftar di database
     * @param id nomor id dari Invoice dalam database
     * @return Invoice sesuai parameter, return null jika error
     */
    @RequestMapping(value="/invoiceStatus/", method = RequestMethod.PUT)
    public Invoice changeInvoiceStatus( @RequestParam(value="id") int id,
                                        @RequestParam(value="status") InvoiceStatus status)
    {
        try {
            DatabaseInvoicePostgre.changeInvoiceStatus(id, status);
            return DatabaseInvoicePostgre.getInvoice(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }
        return null;
    }

    /** menghapus Invoice yang terdaftar di database
     * @param id nomor id dari Invoice dalam database
     * @return boolean true jika berhasil menghapus sesuai parameter
     */
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public Boolean removeInvoice(@PathVariable int id)
    {
        try {
            return DatabaseInvoicePostgre.removeInvoice(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }
        return false;
    }

    /** mendaftarkan Invoice dengan tipe bank payment ke database
     * @param jobIdList list job yang termasuk dalam invoice (hanya mengambil index 0)
     * @param jobseekerId id jobseeker sebagai pemilik invoice
     * @param adminFee admin fee dalam invoice
     * @return Invoice yang terdaftar di database, jika error
     * atau tidak sesuai aturan dbms maka return null
     */
    @RequestMapping(value="/createBankPayment", method = RequestMethod.POST)
    public Invoice addBankPayment(  @RequestParam(value="jobIdList") ArrayList<Integer> jobIdList,
                                    @RequestParam(value="jobseekerId") int jobseekerId,
                                    @RequestParam(value="adminFee", required=false) Integer adminFee)
    {
        if (adminFee == null)
            adminFee = 0;
        try {
            // hanya satu job untuk satu invoice untuk saat ini
            int jobId = jobIdList.get(0);
            // tidak boleh duplikat job yang berstatus OnGoing
            if (DatabaseInvoicePostgre.isJobExist(jobId, InvoiceStatus.OnGoing))
                throw new Exception("Duplicate OnGoing Invoice with Job Id: " + jobId + ", Jobseeker Id: " + jobseekerId);
            Job job = DatabaseJobPostgre.getJob(jobId);
            ArrayList<Job> joblist = new ArrayList<Job>() {{ add(job); }};
            Jobseeker js = DatabaseJobseekerPostgre.getJobseeker(jobseekerId);
            Invoice inv = new BankPayment(0, joblist, js, adminFee);
            int invId = DatabaseInvoicePostgre.insertInvoice(inv);
            inv.setId(invId);
            return inv;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }
        return null;
    }

    /** mendaftarkan Invoice dengan tipe ewallet payment ke database
     * @param jobIdList list job yang termasuk dalam invoice (hanya mengambil index 0)
     * @param jobseekerId id jobseeker sebagai pemilik invoice
     * @param referralCode kode referral untuk mengaplikasikan bonus ke invoice
     * @return Invoice yang terdaftar di database, jika error
     * atau tidak sesuai aturan dbms maka return null
     */
    @RequestMapping(value="/createEWalletPayment", method = RequestMethod.POST)
    public Invoice addEWalletPayment(   @RequestParam(value="jobIdList") ArrayList<Integer> jobIdList,
                                        @RequestParam(value="jobseekerId") int jobseekerId,
                                        @RequestParam(value="referralCode", required=false) String referralCode)
    {
        try {
            // hanya satu job untuk satu invoice untuk saat ini
            int jobId = jobIdList.get(0);
            // tidak boleh duplikat job yang berstatus OnGoing
            if (DatabaseInvoicePostgre.isJobExist(jobId, InvoiceStatus.OnGoing))
                throw new Exception("Duplicate OnGoing Invoice with Job Id: " + jobId + ", Jobseeker Id: " + jobseekerId);
            Job job = DatabaseJobPostgre.getJob(jobId);
            ArrayList<Job> joblist = new ArrayList<Job>() {{ add(job); }};
            Jobseeker js = DatabaseJobseekerPostgre.getJobseeker(jobseekerId);
            Bonus bonus = DatabaseBonusPostgre.getBonusByReferral(referralCode);
            Invoice inv = new EwalletPayment(0, joblist, js, bonus);
            int invId = DatabaseInvoicePostgre.insertInvoice(inv);
            inv.setId(invId);
            return inv;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }
        return null;
    }
}