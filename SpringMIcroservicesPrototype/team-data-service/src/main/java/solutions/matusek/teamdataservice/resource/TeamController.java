package solutions.matusek.teamdataservice.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solutions.matusek.teamdataservice.domain.Team;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Value("${my.greeting:no}")
    private String greetingMessage;

    private static List<Team> teams = Arrays.asList(
        new Team(1, "Aldodesign", "free"),
        new Team(2, "Foundation", "yearly")
    );

    @GetMapping("/greeting")
    public String getTest() {
        return greetingMessage;
    }

    @GetMapping("/{teamId}")
    public ResponseEntity getTeamDetail(@PathVariable(name = "teamId") Integer teamId) {
        Optional<Team> team = teams.stream().filter(t -> t.getId() == teamId).findFirst();
        return team.isPresent() ?
                ResponseEntity.ok(new TeamItemResponse(team.get())) :
                ResponseEntity.notFound().build();
    }
}