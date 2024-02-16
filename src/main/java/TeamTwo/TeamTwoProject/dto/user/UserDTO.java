package TeamTwo.TeamTwoProject.dto.user;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private int id;
    private String userid;
    private String password;
    private String salt;
    private String email;
    private String nickname;
    private String image;
    private String token;

}
