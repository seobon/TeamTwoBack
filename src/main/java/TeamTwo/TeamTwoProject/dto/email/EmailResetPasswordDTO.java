package TeamTwo.TeamTwoProject.dto.email;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmailResetPasswordDTO {

    private String email;
    private String newPassword;
    private String newSalt;
}
