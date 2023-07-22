package uz.pdp.backend.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uz.pdp.backend.entity.User;

import java.util.UUID;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRepository extends BaseRepository<User, UUID> {
private static final UserRepository USER_REPOSITORY=new UserRepository();

    public static UserRepository getInstance() {
        return USER_REPOSITORY;
    }


}
