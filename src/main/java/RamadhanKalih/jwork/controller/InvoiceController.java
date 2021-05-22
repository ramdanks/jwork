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
        return DatabaseInvoice.getInvoiceDatabase();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Invoice getInvoiceById(@PathVariable int id) {
        Invoice var = null;
        try {
            var = DatabaseInvoice.getInvoiceById(id);
        } catch (InvoiceNotFoundException e) {
            System.out.println(e.getMessage());
            var = null;
        }
        return var;
    }

    @RequestMapping(value="/Jobseeker/{JobseekerId}", method = RequestMethod.GET)
    public ArrayList<Invoice> getInvoiceByJobseeker(@PathVariable int id)
    {
        return DatabaseInvoice.getInvoiceByJobseeker(id);
    }

    @RequestMapping(value="/invoiceStatus/", method = RequestMethod.PUT)
    public Invoice changeInvoiceStatus( @RequestParam(value="id") int id,
                                        @RequestParam(value="status") InvoiceStatus status)
    {
        Invoice var = null;
        try {
            var = DatabaseInvoice.getInvoiceById(id);
            var.setInvoiceStatus(status);
        } catch (InvoiceNotFoundException e) {
            System.out.println(e.getMessage());
            var = null;
        }
        return var;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public Boolean removeInvoice(@PathVariable int id)
    {
        try {
            return DatabaseInvoice.removeInvoice(id);
        } catch (InvoiceNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @RequestMapping(value="/createBankPayment", method = RequestMethod.POST)
    public Invoice addBankPayment(  @RequestParam(value="jobIdList") ArrayList<Integer> jobIdList,
                                    @RequestParam(value="jobseekerId") int jobseekerId,
                                    @RequestParam(value="adminFee") int adminFee)
    {
        try {
            ArrayList<Job> jobList = new ArrayList<Job>();
            for (Integer id : jobIdList)
            {
                Job j = DatabaseJob.getJobById(id);
                jobList.add(j);
            }
            Jobseeker js = DatabaseJobseeker.getJobseekerById(jobseekerId);
            int newId = DatabaseInvoice.getLastId() + 1;
            BankPayment p = new BankPayment(newId, jobList, js, adminFee);
            DatabaseInvoice.addInvoice(p);
            return p;
        } catch (JobseekerNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (JobNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (OngoingInvoiceAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @RequestMapping(value="/createEWalletPayment", method = RequestMethod.POST)
    public Invoice addEWalletPayment(   @RequestParam(value="jobIdList") ArrayList<Integer> jobIdList,
                                        @RequestParam(value="jobseekerId") int jobseekerId,
                                        @RequestParam(value="Bonus") String Bonus)
    {
        try {
            ArrayList<Job> jobList = new ArrayList<Job>();
            for (Integer id : jobIdList)
            {
                Job j = DatabaseJob.getJobById(id);
                jobList.add(j);
            }
            Jobseeker js = DatabaseJobseeker.getJobseekerById(jobseekerId);
            Bonus b = DatabaseBonus.getBonusByReferralCode(Bonus);
            int newId = DatabaseInvoice.getLastId() + 1;
            EwalletPayment p = new EwalletPayment(newId, jobList, js, b);
            DatabaseInvoice.addInvoice(p);
            return p;
        } catch (JobseekerNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (JobNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (OngoingInvoiceAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}