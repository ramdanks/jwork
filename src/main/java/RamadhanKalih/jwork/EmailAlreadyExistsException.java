package RamadhanKalih.jwork;

/** Kelas exception untuk email yang sudah terdaftar
 * @author Ramadhan Kalih Sewu (1806148826)
 * @version 210517
 */
public class EmailAlreadyExistsException extends Exception
{
    /** Jobseeker yang memiliki email sama */
    private Jobseeker jobseeker_error;

    /** ctor inisiasi variabel */
    public EmailAlreadyExistsException(Jobseeker jobseeker_input) {
        super("Jobseeker Email: ");
        jobseeker_error = jobseeker_input;
    }
    /** mengembalikan keterangan email yang sudah terdaftar */
    public String getMessage() {
        return super.getMessage() + jobseeker_error.getEmail() + " already exists";
    }
}
