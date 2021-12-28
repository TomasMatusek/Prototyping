package solutions.matusek.userdataservice.resource;

import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import solutions.matusek.userdataservice.domain.Team;
import solutions.matusek.userdataservice.domain.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WebClient.Builder webClient;

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    TeamData teamData;

    @Autowired
    Environment environment;

    @GetMapping("/{userId}")
    public ResponseEntity getUsersByTeamId(@PathVariable(name = "userId") int userId) {
        User user = new User(1, 1, "tomas.matusek@hotmail.co.uk", "Tomas", "Matusek");
        return ResponseEntity.ok(new UserItemResponse(user));
    }

    @GetMapping("/{userId}/teams")
    public ResponseEntity getTeamsByUserId(@PathVariable(name = "userId") int userId) {
        List<Integer> teamsIds = Arrays.asList(1,2);
        List<TeamItemResponse> response = teamsIds.stream().map(teamId -> teamData.getTeamItem(teamId)).collect(Collectors.toList());
        List<Team> teams = response.stream().map(TeamItemResponse::getTeam).collect(Collectors.toList());
        return ResponseEntity.ok(new UserTeamsListResponse(teams));
    }

    @GetMapping("/env")
    public String getEnv() {
        return environment.toString();
    }

    // WebClient instead of RestTemplate
    private TeamItemResponse getTeamUsingWebClient(int teamId) {
        return webClient.build()
                .get()
                .uri("http://localhost:9010/teams/" + teamId)
                .retrieve()
                .bodyToMono(TeamItemResponse.class)
                .block();
    }
}
