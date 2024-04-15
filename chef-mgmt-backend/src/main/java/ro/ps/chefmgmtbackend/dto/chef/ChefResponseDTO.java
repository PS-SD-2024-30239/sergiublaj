package ro.ps.chefmgmtbackend.dto.chef;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChefResponseDTO {

    private UUID id;
    private String name;
    private double numberOfStars;
}
