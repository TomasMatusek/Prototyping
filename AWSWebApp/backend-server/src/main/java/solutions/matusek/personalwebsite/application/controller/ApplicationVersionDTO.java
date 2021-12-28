package solutions.matusek.personalwebsite.application.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
public class ApplicationVersionDTO {
    private String version;
    private String groupId;
    private String artifactId;
}