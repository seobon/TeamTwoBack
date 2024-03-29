package TeamTwo.TeamTwoProject.dto.user;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Getter
@Builder
public class UserDTO {

    private int id;
    // @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String userid;

    // @NotBlank(message = "비밀번호는 필수 입력 값입니다."
    // @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    private String salt;

    // @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    // @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    // @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
    private String nickname;

    private String image;
    private String accessToken;
    private String refreshToken;
    // 주석처리한건 나중에 확인해보고 쓰기
}
