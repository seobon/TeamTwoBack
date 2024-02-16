package TeamTwo.TeamTwoProject.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserTestDTO {
    /**
     * 	   id int not null auto_increment primary key,
     *     user_id varchar(20) not null,
     *     password varchar(255) not null,
     *     salt varchar(255) not null,
     *     email varchar(40) not null,
     *     nickname varchar(20) not null,
     *     image varchar(500)
     */
    private int id;
    private String user_id;
    private String password;
    private String salt;
    private String email;
    private String nickname;
    private String image;

}
