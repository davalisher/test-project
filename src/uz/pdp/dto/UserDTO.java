package uz.pdp.dto;

import uz.pdp.enums.UserType;
import uz.pdp.exception.PasswordNotMatchConfirmPasswordException;

public record UserDTO (String username, String password, String confirmPassword){


    public UserDTO {
        if (username==null||password==null||confirmPassword==null){
            throw new NullPointerException("One of the fields is null!!!");
        }else if (!password.equals(confirmPassword)){
                throw new PasswordNotMatchConfirmPasswordException();
        }
    }
}
