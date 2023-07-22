package uz.pdp.service;

import uz.pdp.dto.UserDTO;
import uz.pdp.entity.User;
import uz.pdp.repository.UserRepository;
import uz.pdp.repository.UserRepositoryImpl;

public class UserServiceImpl implements UserService {
     UserRepository userRepositoryImpl = new UserRepositoryImpl();



    @Override
    public User create(UserDTO userDTO) {
        if (checkUsername(userDTO.username())) {
            return null;
        }
        User user = new User();
        user.setUsername(userDTO.username());
        user.setPassword(userDTO.password());
        userRepositoryImpl.USERS.add(user);
//        userRepositoryImpl.USERS.forEach(System.out::println);
        return user;
    }

    @Override
    public User get(String username, String password) {
        for (User user : userRepositoryImpl.USERS) {
            if (user.getUsername().equals(username)&& user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    private boolean checkUsername(String username) {
        for (User user : userRepositoryImpl.USERS) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
