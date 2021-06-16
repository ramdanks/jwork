package RamadhanKalih.jwork.controller;

import RamadhanKalih.jwork.*;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RequestMapping("/invoice")
@RestController
public class InvoiceController
{

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

    @RequestMapping(value="/createBankPayment", method = RequestMethod.POST)
    public Invoice addBankPayment(  @RequestParam(value="jobIdList") ArrayList<Integer> jobIdList,
                                    @RequestParam(value="jobseekerId") int jobseekerId,
                                    @RequestParam(value="adminFee", required=false) Integer adminFee)
    {
        if (adminFee == null)
            adminFee = 0;
        try {
            // currently can only take one job in one invoice
            int jobId = jobIdList.get(0);
            // should only have one OnGoing job from a jobseeker
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

    @RequestMapping(value="/createEWalletPayment", method = RequestMethod.POST)
    public Invoice addEWalletPayment(   @RequestParam(value="jobIdList") ArrayList<Integer> jobIdList,
                                        @RequestParam(value="jobseekerId") int jobseekerId,
                                        @RequestParam(value="referralCode", required=false) String referralCode)
    {
        try {
            // currently can only take one job in one invoice
            int jobId = jobIdList.get(0);
            // should only have one OnGoing job from a jobseeker
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