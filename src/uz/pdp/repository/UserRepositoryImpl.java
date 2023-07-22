package uz.pdp.repository;

import uz.pdp.entity.User;
import uz.pdp.enums.UserType;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    static {
        USERS.add(new User("admin","root123456","123456","admin@gmail.com", UserType.ADMIN));
    }

}
