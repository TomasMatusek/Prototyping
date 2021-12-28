package solutions.matusek.personalwebsite.application.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationRestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private IApplicationRestController serviceRestController;

    @Test
    void ContextLoads() {
        Assertions.assertNotNull(serviceRestController);
    }

    @Test
    void When_RootHit_Then_EmptyJsonReturned() {
        ResponseEntity<String> response = restTemplate.getForEntity(getUrl("/"), String.class);
        Assertions.assertNotNull(response);
        Assertions.assertEquals("{\"message\":\"Hello, World 123!\"}", response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void When_VersionHit_Then_VersionJsonReturned() {
        ResponseEntity<ApplicationVersionDTO> response = restTemplate.getForEntity(getUrl("/version"), ApplicationVersionDTO.class);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("matusek-solutions-backend-server", response.getBody().getArtifactId());
        Assertions.assertEquals("solutions.matusek", response.getBody().getGroupId());
    }

    private String getUrl(String path) {
        return "http://localhost:" + port + "/" + path;
    }
}