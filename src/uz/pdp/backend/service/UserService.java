package uz.pdp.backend.service;

import uz.pdp.backend.dto.UserDTO;
import uz.pdp.backend.entity.User;
import uz.pdp.backend.enums.UserType;
import uz.pdp.backend.repository.UserRepository;

import java.util.List;
import java.util.UUID;

public class UserService extends BaseService<User, UUID, UserDTO> {
    UserRepository userRepository = UserRepository.getInstance();

    {
        userRepository.add(new User("admin", "123", "123456", "admin@gmail.com", UserType.ADMIN));
        userRepository.add(new User("user", "1", "123456", "user@gmail.com", UserType.USER));
    }


    @Override
    public boolean create(UserDTO userDTO) {
        if (checkUsername(userDTO.username())) {
            return false;
        }
        User user = new User();
        user.setUsername(userDTO.username());
        user.setPassword(userDTO.password());
        user.setUserType(UserType.USER);
        if (userDTO.createdBy() != null)
            user.setCreatedBy(user.getId());
        else
            user.setCreatedBy(user.getId());
        userRepository.add(user);
        return true;
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }
    public User get(String username, String password) {
        for (User user : userRepository.getAll()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private boolean checkUsername(String username) {
        for (User user : userRepository.getAll()) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
