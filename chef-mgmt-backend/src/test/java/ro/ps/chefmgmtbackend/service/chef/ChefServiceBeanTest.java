package ro.ps.chefmgmtbackend.service.chef;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ro.ps.chefmgmtbackend.dto.chef.ChefResponseDTO;
import ro.ps.chefmgmtbackend.exception.NotFoundException;
import ro.ps.chefmgmtbackend.mapper.ChefMapper;
import ro.ps.chefmgmtbackend.model.ChefEntity;
import ro.ps.chefmgmtbackend.repository.ChefRepository;

@SpringBootTest
class ChefServiceBeanTest {

    private static final UUID CHEF_ID = UUID.randomUUID();

    private ChefServiceBean underTest;

    @Mock
    private ChefRepository chefRepositoryMock;

    @Mock
    private ChefMapper chefMapperMock;

    @BeforeEach
    void setUp() {
        this.underTest = new ChefServiceBean(
                chefRepositoryMock,
                chefMapperMock,
                null
        );
    }

    @Test
    void givenValidChefId_whenFindByIdIsCalled_thenReturnChefResponseDTO() {
        final var chefEntity = getChefEntity();
        final var chefResponseDTO = getChefResponseDTO();
        when(chefRepositoryMock.findById(any(UUID.class))).thenReturn(Optional.of(chefEntity));
        when(chefMapperMock.chefEntityToChefResponseDTO(any(ChefEntity.class))).thenReturn(chefResponseDTO);

        final var response = underTest.findById(CHEF_ID);

        assertThat(response).isEqualTo(chefResponseDTO);
        verify(chefRepositoryMock).findById(any(UUID.class));
        verify(chefMapperMock).chefEntityToChefResponseDTO(any(ChefEntity.class));
    }

    @Test
    void givenInvalidChefId_whenFindByIdIsCalled_thenThrowException() {
        final var chefResponseDTO = getChefResponseDTO();
        when(chefRepositoryMock.findById(any(UUID.class))).thenReturn(Optional.empty());
        when(chefMapperMock.chefEntityToChefResponseDTO(any(ChefEntity.class))).thenReturn(chefResponseDTO);

        assertThatThrownBy(() -> underTest.findById(CHEF_ID))
                .isInstanceOf(NotFoundException.class);
        verify(chefRepositoryMock).findById(any(UUID.class));
        verify(chefMapperMock, never()).chefEntityToChefResponseDTO(any(ChefEntity.class));
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