package solutions.matusek.personalwebsite.application.controller;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IApplicationRestController {
    ResponseEntity<Map<String,String>> getRoot();
    ResponseEntity<ApplicationVersionDTO> getVersion();
}
