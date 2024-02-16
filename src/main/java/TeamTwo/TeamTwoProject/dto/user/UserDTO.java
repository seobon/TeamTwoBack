package TeamTwo.TeamTwoProject.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
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
