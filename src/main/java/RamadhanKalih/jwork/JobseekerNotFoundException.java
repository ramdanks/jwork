package RamadhanKalih.jwork;

/** Kelas exception untuk Jobseeker yang tidak ditemukan
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210517
 */
public class JobseekerNotFoundException extends Exception
{
    /** id jobseeker yang bermasalah */
    private int jobseeker_error;

    /** ctor inisiasi variabel */
    public JobseekerNotFoundException(int jobseeker_input) {
        super("Jobseeker ID: ");
        jobseeker_error = jobseeker_input;
    }
    /** mengembalikan keterangan jobseeker yang tidak ditemukan */
    public String getMessage() {
        return super.getMessage() + jobseeker_error + " not found";
    }
}
