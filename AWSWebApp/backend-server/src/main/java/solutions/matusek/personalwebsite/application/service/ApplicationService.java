package solutions.matusek.personalwebsite.application.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Service
public class ApplicationService implements IApplicationService {

    private final String version;
    private final String groupId;
    private final String artifactId;

    @Autowired
    public ApplicationService(
            @Value("${pom.project.version}") String version,
            @Value("${pom.project.groupId}") String groupId,
            @Value("${pom.project.artifactId}") String artifactId) {
        this.version = version;
        this.groupId = groupId;
        this.artifactId = artifactId;
    }
}