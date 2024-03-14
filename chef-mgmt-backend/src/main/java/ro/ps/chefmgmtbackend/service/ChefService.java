package ro.ps.chefmgmtbackend.service;

import ro.ps.chefmgmtbackend.dto.ChefRequestDTO;
import ro.ps.chefmgmtbackend.dto.ChefResponseDTO;

import java.util.List;

public interface ChefService {

    List<ChefResponseDTO> findAll();

    List<ChefResponseDTO> findAllByRatingGreaterThan(double rating);

    ChefResponseDTO save(ChefRequestDTO chefRequestDTO);
}
