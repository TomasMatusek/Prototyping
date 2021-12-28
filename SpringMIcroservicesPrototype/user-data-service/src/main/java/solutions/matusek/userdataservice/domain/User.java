package solutions.matusek.userdataservice.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private int teamId;
    private String email;
    private String firstName;
    private String lastName;
}