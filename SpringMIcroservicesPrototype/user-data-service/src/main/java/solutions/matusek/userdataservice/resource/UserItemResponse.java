package solutions.matusek.userdataservice.resource;

import lombok.*;
import solutions.matusek.userdataservice.domain.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class UserItemResponse {
    private User user;
}
