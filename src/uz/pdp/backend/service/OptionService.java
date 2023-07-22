package uz.pdp.backend.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uz.pdp.backend.dto.OptionDTO;
import uz.pdp.backend.entity.Option;
import uz.pdp.backend.repository.OptionRepository;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OptionService extends BaseService<Option, UUID, OptionDTO>{
    private static final OptionService optionService = new OptionService();

    public static OptionService getInstance() {
        return optionService;
    }
    OptionRepository optionRepository = OptionRepository.getInstance();

    @Override
    public boolean create(OptionDTO optionDTO) {
        Option option = new Option(optionDTO.text(), optionDTO.correct());
        optionRepository.add(option);
        return true;
    }

    @Override
    public List<Option> getAll() {
        return optionRepository.getAll();
    }
}
