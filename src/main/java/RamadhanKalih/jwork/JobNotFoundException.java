package RamadhanKalih.jwork;

/** Kelas exception untuk Job yang tidak ditemukan
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210517
 */
public class JobNotFoundException extends Exception
{
    /** id job yang bermasalah */
    private int job_error;

    /** ctor inisiasi variabel */
    public JobNotFoundException(int job_input) {
        super("Job ID: ");
        job_error = job_input;
    }
    
    /** mengembalikan keterangan job yang tidak ditemukan */
    public String getMessage() {
        return super.getMessage() + job_error + " not found";
    }
}
