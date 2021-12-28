package solutions.matusek.userdataservice.resource;

import lombok.*;
import solutions.matusek.userdataservice.domain.Team;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
class UserTeamsListResponse {
    private List<Team> teams;

    public UserTeamsListResponse() {
        this.teams = Collections.emptyList();
    }
}