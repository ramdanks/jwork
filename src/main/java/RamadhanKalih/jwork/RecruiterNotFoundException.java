package RamadhanKalih.jwork;

/** Kelas exception untuk recruiter yang tidak ditemukan
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210517
 */
public class RecruiterNotFoundException extends Exception
{
    /** Recruiter yang bermasalah */
    private int recruiter_error;

    /** ctor inisiasi variabel */
    public RecruiterNotFoundException(int recruiter_input) {
        super("Recruiter ID: ");
        recruiter_error = recruiter_input;
    }
    /** mengembalikan keterangan recruiter yang tidak ditemukan */
    public String getMessage() {
        return super.getMessage() + recruiter_error + " not found";
    }
}
