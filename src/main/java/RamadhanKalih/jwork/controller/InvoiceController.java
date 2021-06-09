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

    @RequestMapping(value="/jobseeker/{jobseekerId}", method = RequestMethod.GET)
    public ArrayList<Invoice> getInvoiceByJobseeker(@PathVariable int jobseekerId)
    {
        return DatabaseInvoice.getInvoiceByJobseeker(jobseekerId);
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
                                    @RequestParam(value="adminFee", required=false) Integer adminFee)
    {
        if (adminFee == null)
            adminFee = 0;
        try {
            // dalam satu request, kita hanya boleh membuat satu invoice untuk satu job
            Job jobApplied = DatabaseJob.getJobById(jobIdList.get(0));
            // ambil data dari database
            ArrayList<Invoice> jobseekerInvoiceList = DatabaseInvoice.getInvoiceByJobseeker(jobseekerId);
            // pastikan bahwa jobseeker tidak apply lebih dari satu job
            if (duplicateOnGoingJob(jobseekerInvoiceList, jobApplied))
                throw new Exception(String.format("Duplicate Job (id: %d) by Jobseeker id: %d", jobApplied.getId(), jobseekerId));
            // buat invoice dan masukan kedalam database
            ArrayList<Job> joblist = new ArrayList<Job>();
            joblist.add(jobApplied);
            Jobseeker js = DatabaseJobseeker.getJobseekerById(jobseekerId);
            int newId = DatabaseInvoice.getLastId() + 1;
            Invoice invoice = new BankPayment(newId, joblist, js, adminFee);
            DatabaseInvoice.addInvoice(invoice);
            return invoice;
        } catch (JobseekerNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (JobNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (OngoingInvoiceAlreadyExistsException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @RequestMapping(value="/createEWalletPayment", method = RequestMethod.POST)
    public Invoice addEWalletPayment(   @RequestParam(value="jobIdList") ArrayList<Integer> jobIdList,
                                        @RequestParam(value="jobseekerId") int jobseekerId,
                                        @RequestParam(value="referralCode", required=false) String referralCode)
    {
        if (referralCode == null)
            referralCode = "";
            try {
                // dalam satu request, kita hanya boleh membuat satu invoice untuk satu job
                Job jobApplied = DatabaseJob.getJobById(jobIdList.get(0));
                // ambil data dari database
                ArrayList<Invoice> jobseekerInvoiceList = DatabaseInvoice.getInvoiceByJobseeker(jobseekerId);
                // pastikan bahwa jobseeker tidak apply lebih dari satu job
                if (duplicateOnGoingJob(jobseekerInvoiceList, jobApplied))
                    throw new Exception(String.format("Duplicate Job (id: %d) by Jobseeker id: %d", jobApplied.getId(), jobseekerId));
                // buat invoice dan masukan kedalam database
                ArrayList<Job> joblist = new ArrayList<Job>();
                joblist.add(jobApplied);
                Jobseeker js = DatabaseJobseeker.getJobseekerById(jobseekerId);
                int newId = DatabaseInvoice.getLastId() + 1;
                Bonus bonus = DatabaseBonus.getBonusByReferralCode(referralCode);
                Invoice invoice = new EwalletPayment(newId, joblist, js, bonus);
                DatabaseInvoice.addInvoice(invoice);
                return invoice;
            } catch (JobseekerNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (JobNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (OngoingInvoiceAlreadyExistsException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return null;
    }

    private boolean duplicateOnGoingJob(ArrayList<Invoice> jobseekerInvoiceList, Job jobApplied)
    {
        if (jobseekerInvoiceList == null)
            return false;
        for (Invoice inv : jobseekerInvoiceList)
        {
            // invoice berstatus selain OnGoing tidak apa-apa duplikat
            if (inv.getInvoiceStatus() != InvoiceStatus.OnGoing)
                continue;
            // satu Job hanya boleh memiliki satu invoice berstatus onGoing
            Job storedJob = inv.getJobs().get(0);
            if (storedJob.getId() == jobApplied.getId())
                return true;
        }
        return false;
    }
}