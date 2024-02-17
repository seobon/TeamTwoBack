package TeamTwo.TeamTwoProject.dto.user;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserUpdateDTO {

    private String currentPassword;
    private String newPassword;
    private String newNickName;
}
