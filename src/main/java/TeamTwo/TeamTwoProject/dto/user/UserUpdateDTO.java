package TeamTwo.TeamTwoProject.dto.user;



import lombok.*;

@Getter
@Builder
public class UserUpdateDTO {

    private String currentPassword;
    private String newPassword;
    private String newNickName;
}
