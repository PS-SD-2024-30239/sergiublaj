package ro.ps.chefmgmtbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.ps.chefmgmtbackend.dto.CollectionResponseDTO;
import ro.ps.chefmgmtbackend.dto.PageRequestDTO;
import ro.ps.chefmgmtbackend.dto.chef.ChefRequestDTO;
import ro.ps.chefmgmtbackend.dto.chef.ChefResponseDTO;
import ro.ps.chefmgmtbackend.exception.ExceptionBody;
import ro.ps.chefmgmtbackend.service.chef.ChefService;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/chef/v1")
@RequiredArgsConstructor
public class ChefController {

    private final ChefService chefService;

    @GetMapping("/{id}")
    @Operation(summary = "Gets chef by ID", description = "Chef must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Chef found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ChefResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Chef not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<ChefResponseDTO> findById(@PathVariable("id") UUID chefId) {
        return new ResponseEntity<>(
                chefService.findById(chefId),
                HttpStatus.OK
        );
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<ChefResponseDTO>> findAll() {
        return new ResponseEntity<>(
                chefService.findAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/all-paged")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CollectionResponseDTO<ChefResponseDTO>> findAllPaged(@Valid PageRequestDTO page) {
        return new ResponseEntity<>(
                chefService.findAllPaged(page),
                HttpStatus.OK
        );
    }

    @GetMapping("/all-sorted")
    public ResponseEntity<List<ChefResponseDTO>> findAllSorted(
            @RequestParam(value = "sortBy", defaultValue = "", required = false) String sortBy
    ) {
        return new ResponseEntity<>(
                chefService.findAllSorted(sortBy),
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
            @RequestParam(value = "rating", defaultValue = "10", required = false) double rating
    ) {
        return new ResponseEntity<>(
                chefService.findAllByRatingGreaterThan(rating),
                HttpStatus.OK
        );
    }

    @PostMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ChefResponseDTO> saveChef(
            @RequestBody ChefRequestDTO chefRequestDTO
    ) {
        return new ResponseEntity<>(
                chefService.save(chefRequestDTO),
                HttpStatus.CREATED
        );
    }
}
