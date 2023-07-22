package uz.pdp.backend.entity;

import lombok.*;
import uz.pdp.backend.enums.UserType;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class User extends BaseEntity<UUID> {
    {
        id =UUID.randomUUID();
    }

    private String username;
    private String password;
    private String phone;
    private String email;
    private UserType userType;
    private List<Answer> answers;

    public User() {
//        uuid=UUID.randomUUID();
    }

    public User(String username, String password, String phone, String email, UserType userType) {
//        uuid=UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.userType=userType;
    }


}
