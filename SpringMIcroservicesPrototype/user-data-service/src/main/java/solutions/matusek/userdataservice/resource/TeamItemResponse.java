package solutions.matusek.userdataservice.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import solutions.matusek.userdataservice.domain.Team;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamItemResponse {
    private Team team;
}

