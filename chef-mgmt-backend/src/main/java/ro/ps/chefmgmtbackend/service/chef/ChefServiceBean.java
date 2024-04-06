package ro.ps.chefmgmtbackend.service.chef;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import ro.ps.chefmgmtbackend.dto.chef.ChefRequestDTO;
import ro.ps.chefmgmtbackend.dto.chef.ChefResponseDTO;
import ro.ps.chefmgmtbackend.dto.CollectionResponseDTO;
import ro.ps.chefmgmtbackend.dto.PageRequestDTO;
import ro.ps.chefmgmtbackend.exception.ExceptionCode;
import ro.ps.chefmgmtbackend.exception.NotFoundException;
import ro.ps.chefmgmtbackend.mapper.ChefMapper;
import ro.ps.chefmgmtbackend.model.ChefEntity;
import ro.ps.chefmgmtbackend.repository.ChefRepository;

@Slf4j
@RequiredArgsConstructor
public class ChefServiceBean implements ChefService {

    private final ChefRepository chefRepository;
    private final ChefMapper chefMapper;
    private final String applicationName;

    @Override
    public ChefResponseDTO findById(UUID chefId) {
        return chefRepository.findById(chefId)
                .map(chefMapper::chefEntityToChefResponseDTO)
                .orElseThrow(() -> new NotFoundException(String.format(
                        ExceptionCode.ERR001_CHEF_NOT_FOUND.getMessage(),
                        chefId
                )));
    }

    @Override
    public List<ChefResponseDTO> findAll() {
        log.info("Getting all chefs for application {}", applicationName);

        List<ChefEntity> chefEntityList = chefRepository.findAll();

        return chefMapper.chefEntityListToChefResponseDTOList(chefEntityList);
    }

    @Override
    public CollectionResponseDTO<ChefResponseDTO> findAllPaged(PageRequestDTO page) {
        Page<ChefEntity> chefEntityList = chefRepository.findAll(PageRequest.of(
                page.getPageNumber(),
                page.getPageSize()
        ));
        List<ChefResponseDTO> chefs = chefMapper.chefEntityListToChefResponseDTOList(chefEntityList.getContent());

        return new CollectionResponseDTO<>(chefs, chefEntityList.getTotalElements());
    }

    @Override
    public List<ChefResponseDTO> findAllSorted(String sortBy) {
        List<ChefEntity> chefEntityList = chefRepository.findAll(Sort.by(sortBy).descending());

        return chefMapper.chefEntityListToChefResponseDTOList(chefEntityList);
    }

    @Override
    public List<ChefResponseDTO> findAllByRatingGreaterThan(double rating) {
        List<ChefEntity> chefEntityList = chefRepository.findAllByRatingGreaterThan(rating);

        return chefMapper.chefEntityListToChefResponseDTOList(chefEntityList);
    }

    @Override
    @Transactional
    public ChefResponseDTO save(ChefRequestDTO chefRequestDTO) {
        ChefEntity chefToBeAdded = chefMapper.chefRequestDTOToChefEntity(chefRequestDTO);
        ChefEntity chefAdded = chefRepository.save(chefToBeAdded);

        return chefMapper.chefEntityToChefResponseDTO(chefAdded);
    }
}
