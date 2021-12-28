package solutions.matusek.personalwebsite.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import solutions.matusek.personalwebsite.application.service.IApplicationService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class ApplicationRestController implements IApplicationRestController {

    IApplicationService applicationService;

    @Autowired
    public ApplicationRestController(IApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @Override
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,String>> getRoot() {
        Map<String,String> response = new HashMap<>();
        response.put("message", "Hello, World 123!");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Override
    @GetMapping(value = "/version", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApplicationVersionDTO> getVersion() {
        ApplicationVersionDTO response = new ApplicationVersionDTO(
                applicationService.getVersion(),
                applicationService.getGroupId(),
                applicationService.getArtifactId()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}