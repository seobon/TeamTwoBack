package TeamTwo.TeamTwoProject.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "user")
public class UserEntity {

    @Id
    // @GeneratedValue(strategy = GenerationType.UUID) 번호가 아니므로 그닥 쑬 이유를 모르겠다...
    @Column(name = "user_id", nullable = false)
    private String user_id;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "salt", nullable = false)
    private String salt;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "nickname", nullable = false)
    private String nickname;

//    @Column(name = "image")
//    private String image;
}
