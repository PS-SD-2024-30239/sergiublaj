package ro.ps.chefmgmtbackend.service.chef;

import java.util.List;
import java.util.UUID;

import ro.ps.chefmgmtbackend.dto.chef.ChefRequestDTO;
import ro.ps.chefmgmtbackend.dto.chef.ChefResponseDTO;
import ro.ps.chefmgmtbackend.dto.CollectionResponseDTO;
import ro.ps.chefmgmtbackend.dto.PageRequestDTO;

/**
 * Service class that defines Chef CRUD operations
 * This includes:
 * - Finding a chef by id
 * - Finding all chefs
 * - Finding all chefs with a given rating
 * - Saving a chef
 */
public interface ChefService {

    ChefResponseDTO findById(UUID chefId);

    List<ChefResponseDTO> findAll();

    CollectionResponseDTO<ChefResponseDTO> findAllPaged(PageRequestDTO page);

    List<ChefResponseDTO> findAllSorted(String sortBy);

    /**
     * Method that returns all chefs with a rating greater than a given rating
     *
     * @param rating - reference rating
     * @return list of chefs with requested rating
     */
    List<ChefResponseDTO> findAllByRatingGreaterThan(double rating);

    ChefResponseDTO save(ChefRequestDTO chefRequestDTO);
}
