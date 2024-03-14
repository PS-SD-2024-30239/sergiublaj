package ro.ps.chefmgmtbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ps.chefmgmtbackend.dto.ChefRequestDTO;
import ro.ps.chefmgmtbackend.dto.ChefResponseDTO;
import ro.ps.chefmgmtbackend.service.ChefService;

import java.util.List;

@RestController
@RequestMapping("/chef/v1")
@RequiredArgsConstructor
public class ChefController {

    private final ChefService chefService;

    @GetMapping("/all")
    public ResponseEntity<List<ChefResponseDTO>> findAll() {
        return new ResponseEntity<>(
                chefService.findAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/all2/{rating}")
    public ResponseEntity<List<ChefResponseDTO>> findAllByRating(
            @PathVariable("rating") double rating
    ) {
        return new ResponseEntity<>(
                chefService.findAllByRatingGreaterThan(rating),
                HttpStatus.OK
        );
    }

    @GetMapping("/all3")
    public ResponseEntity<List<ChefResponseDTO>> findAllByRating2(
            @RequestParam("rating") double rating
    ) {
        return new ResponseEntity<>(
                chefService.findAllByRatingGreaterThan(rating),
                HttpStatus.OK
        );
    }

    @PostMapping("/all")
    public ResponseEntity<ChefResponseDTO> saveChef(
            @RequestBody ChefRequestDTO chefRequestDTO
    ) {
        return new ResponseEntity<>(
                chefService.save(chefRequestDTO),
                HttpStatus.CREATED
        );
    }
}
