package uz.pdp.backend.dto;

import uz.pdp.backend.exception.PasswordNotMatchConfirmPasswordException;

public record UserDTO<ID> (String username, String password, String confirmPassword, ID createdBy){


    public UserDTO {
        if (username==null||password==null||confirmPassword==null){
            throw new NullPointerException("One of the fields is null!!!");
        }else if (!password.equals(confirmPassword)){
                throw new PasswordNotMatchConfirmPasswordException();
        }
    }
}
