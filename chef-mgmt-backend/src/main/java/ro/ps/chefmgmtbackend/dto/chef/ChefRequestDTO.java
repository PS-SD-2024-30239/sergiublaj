package ro.ps.chefmgmtbackend.dto.chef;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChefRequestDTO {

    private String name;
    private double rating;
}
