package uz.pdp.backend.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uz.pdp.backend.entity.Option;

import java.util.UUID;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OptionRepository extends BaseRepository<Option, UUID> {
    private static final OptionRepository OPTION_REPOSITORY=new OptionRepository();

    public static OptionRepository getInstance() {
        return OPTION_REPOSITORY;
    }
}
