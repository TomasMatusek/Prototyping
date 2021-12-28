package solutions.matusek.teamdataservice.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import solutions.matusek.teamdataservice.domain.Team;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamItemResponse {
    private Team team;
}
