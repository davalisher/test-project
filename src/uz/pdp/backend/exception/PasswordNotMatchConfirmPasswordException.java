package uz.pdp.backend.exception;

public class PasswordNotMatchConfirmPasswordException extends RuntimeException {
    public PasswordNotMatchConfirmPasswordException() {
        super("Password and Confirm password don't match");
    }
}
