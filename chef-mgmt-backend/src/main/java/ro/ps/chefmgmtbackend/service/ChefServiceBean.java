package ro.ps.chefmgmtbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.ps.chefmgmtbackend.dto.ChefRequestDTO;
import ro.ps.chefmgmtbackend.dto.ChefResponseDTO;
import ro.ps.chefmgmtbackend.mapper.ChefMapper;
import ro.ps.chefmgmtbackend.model.ChefEntity;
import ro.ps.chefmgmtbackend.repository.ChefRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChefServiceBean implements ChefService {

    private final ChefRepository chefRepository;
    private final ChefMapper chefMapper;

    @Override
    public List<ChefResponseDTO> findAll() {
        List<ChefEntity> chefEntityList = chefRepository.findAll();

        return chefMapper.chefEntityListToChefResponseDTOList(chefEntityList);
    }

    @Override
    public List<ChefResponseDTO> findAllByRatingGreaterThan(double rating) {
        List<ChefEntity> chefEntityList = chefRepository.findAllByRatingGreaterThan(rating);

        return chefMapper.chefEntityListToChefResponseDTOList(chefEntityList);
    }

    @Override
    public ChefResponseDTO save(ChefRequestDTO chefRequestDTO) {
        ChefEntity chefToBeAdded = chefMapper.chefRequestDTOToChefEntity(chefRequestDTO);
        ChefEntity chefAdded = chefRepository.save(chefToBeAdded);

        return chefMapper.chefEntityToChefResponseDTO(chefAdded);
    }
}
