
// @Author: Ramadhan Kalih Sewu (1806148826)
// @Version: 210318

public class Invoice
{
    private int id;
    private int idJob;
    private String date;
    private int totalFee;
    private Jobseeker jobseeker;
    
    public Invoice(int id, int idJob, String date, int totalFee,
                    Jobseeker jobseeker) {
        this.id = id;
        this.idJob = idJob;
        this.date = date;
        this.totalFee = totalFee;
        this.jobseeker = jobseeker;
    }
    public int getId() {
        return this.id;
    }
    public int getIdJob() {
        return this.idJob;
    }
    public String getDate() {
        return this.date;
    }
    public int getTotalFee() {
        return this.totalFee;
    }
    public Jobseeker getJobseeker() {
        return this.jobseeker;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setIdJobs(int idJob) {
        this.idJob = idJob;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }
    public void setJobseeker(Jobseeker jobseeker) {
        this.jobseeker = jobseeker;
    }
    public void printData() {
        System.out.println("id       : " + this.id);
        System.out.println("idJob    : " + this.idJob);
        System.out.println("date     : " + this.date);
        System.out.println("totalFee : " + this.totalFee);
        System.out.println("jobseeker: " + this.jobseeker.getName());
    }
}
