package uz.pdp.backend.exception;

import uz.pdp.frontend.UserUtil;
import uz.pdp.frontend.Util;

public class PasswordNotMatchConfirmPasswordException extends RuntimeException {
    public PasswordNotMatchConfirmPasswordException() {
        super("Password and Confirm password don't match");
    }
}
