package ro.ps.chefmgmtbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ro.ps.chefmgmtbackend.dto.chef.ChefRequestDTO;
import ro.ps.chefmgmtbackend.dto.chef.ChefResponseDTO;
import ro.ps.chefmgmtbackend.model.ChefEntity;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChefMapper {

    @Mapping(target = "numberOfStars", source = "rating")
    ChefResponseDTO chefEntityToChefResponseDTO(ChefEntity chefEntity);

    List<ChefResponseDTO> chefEntityListToChefResponseDTOList(List<ChefEntity> chefEntityList);

    ChefEntity chefRequestDTOToChefEntity(ChefRequestDTO chefRequestDTO);
}
