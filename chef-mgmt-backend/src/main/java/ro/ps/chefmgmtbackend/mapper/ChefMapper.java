package ro.ps.chefmgmtbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.ps.chefmgmtbackend.dto.ChefRequestDTO;
import ro.ps.chefmgmtbackend.dto.ChefResponseDTO;
import ro.ps.chefmgmtbackend.model.ChefEntity;

import java.util.List;

@Mapper
public interface ChefMapper {

    @Mapping(target = "numberOfStars", source = "rating")
    ChefResponseDTO chefEntityToChefResponseDTO(ChefEntity chefEntity);

    List<ChefResponseDTO> chefEntityListToChefResponseDTOList(List<ChefEntity> chefEntityList);

    ChefEntity chefRequestDTOToChefEntity(ChefRequestDTO chefRequestDTO);
}
