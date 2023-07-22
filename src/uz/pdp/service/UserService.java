package uz.pdp.service;

import uz.pdp.dto.UserDTO;
import uz.pdp.entity.User;

public interface UserService {
    User create(UserDTO userDTO);
    User get(String username, String password);
    boolean update(User user);
    boolean delete(User user);



}
