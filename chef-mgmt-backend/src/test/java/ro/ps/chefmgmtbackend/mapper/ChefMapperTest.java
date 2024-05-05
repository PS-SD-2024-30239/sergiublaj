package ro.ps.chefmgmtbackend.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;
import ro.ps.chefmgmtbackend.dto.chef.ChefResponseDTO;
import ro.ps.chefmgmtbackend.model.ChefEntity;

@SpringBootTest
class ChefMapperTest {

    private ChefMapper underTest;

    private static final UUID CHEF_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    @BeforeEach
    void setUp() {
        this.underTest = Mappers.getMapper(ChefMapper.class);
    }

    @Test
    void givenValidChefEntity_whenMapperCalled_thenReturnValidChefResponseDTO() {
        final var request = getChefEntity();
        final var expected = getChefResponseDTO();

        final var response = underTest.chefEntityToChefResponseDTO(request);

        assertThat(response).isEqualTo(expected);
    }

    private ChefEntity getChefEntity() {
        return ChefEntity.builder()
                .id(CHEF_ID)
                .name("John Doe")
                .rating(5.0d)
                .build();
    }

    private ChefResponseDTO getChefResponseDTO() {
        return ChefResponseDTO.builder()
                .id(CHEF_ID)
                .name("John Doe")
                .numberOfStars(5.0d)
                .build();
    }
}