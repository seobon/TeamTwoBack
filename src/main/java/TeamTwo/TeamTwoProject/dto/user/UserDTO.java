package TeamTwo.TeamTwoProject.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {

    private String user_id;
    private String password;
    private String salt;
    private String email;
    private String nickname;
    // private String image; ==== 이 부분 빼고 저장중

}
