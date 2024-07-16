package validation;

public class Valid {
    //The minimum and maximum length for username
    private int minLength;
    private int maxLength;
    private boolean allowSpecialChar;

    public Valid(int minLength, int maxLength, boolean charChoice) {
        this.minLength = minLength;
        this.maxLength = maxLength;
        allowSpecialChar = charChoice;
    }

}
