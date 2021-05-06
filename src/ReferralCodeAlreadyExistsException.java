public class ReferralCodeAlreadyExistsException extends Exception
{
    private Bonus bonus_error;

    public ReferralCodeAlreadyExistsException(Bonus bonus_input) {
        super("Bonus Code: ");
        bonus_error = bonus_input;
    }
    public String getMessage() {
        return super.getMessage() + bonus_error.getReferralCode() + " already exists";
    }
}
