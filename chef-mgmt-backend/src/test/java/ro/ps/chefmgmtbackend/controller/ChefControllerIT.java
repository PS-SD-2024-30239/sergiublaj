package ro.ps.chefmgmtbackend.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import ro.ps.chefmgmtbackend.dto.chef.ChefResponseDTO;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChefControllerIT {

    private static String API_URL = "http://localhost:{PORT}/api/chef/v1";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private Integer port;

    @Value("${security.invalid-jwt-token}")
    private String invalidJwtToken;

    @Value("${security.admin-jwt-token}")
    private String adminJwtToken;

    @BeforeEach
    public void setUp() {
        API_URL = API_URL.replace("{PORT}", port.toString());
    }

    @Test
    void givenInvalidJwtToken_whenFindByIdIsCalled_thenReturnAccessForbidden() {
        final var chefId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        final var headers = getHeadersEntity(invalidJwtToken);

        final var response = testRestTemplate.exchange(
                String.format("%s/%s", API_URL, chefId),
                HttpMethod.GET,
                headers,
                ChefResponseDTO.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    void givenValidChefId_whenFindByIdIsCalled_thenReturnChefResponseDTO() {
        final var chefId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        final var headers = getHeadersEntity(adminJwtToken);
        final var expected = getChefResponseDTO();

        final var response = testRestTemplate.exchange(
                String.format("%s/%s", API_URL, chefId),
                HttpMethod.GET,
                headers,
                ChefResponseDTO.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expected);
    }

    @Test
    void givenInvalidChefId_whenFindByIdIsCalled_thenThrowException() {
        final var chefId = UUID.fromString("99999999-9999-9999-9999-999999999999");
        final var headers = getHeadersEntity(adminJwtToken);

        final var response = testRestTemplate.exchange(
                String.format("%s/%s", API_URL, chefId),
                HttpMethod.GET,
                headers,
                ChefResponseDTO.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private HttpEntity<?> getHeadersEntity(String jwtToken) {
        final var headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);

        return new HttpEntity<>(null, headers);
    }

    private ChefResponseDTO getChefResponseDTO() {
        return ChefResponseDTO.builder()
                .id(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .name("John Doe")
                .numberOfStars(5.0d)
                .build();
    }
}