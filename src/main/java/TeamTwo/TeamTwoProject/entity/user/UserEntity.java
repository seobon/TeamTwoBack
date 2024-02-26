package TeamTwo.TeamTwoProject.entity.user;

import TeamTwo.TeamTwoProject.dto.diary.DiaryDTO;
import TeamTwo.TeamTwoProject.entity.toDoList.ToDoListEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "user")

public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "userid", nullable = false)
    private String userid;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "salt", nullable = false)
    private String salt;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "image")
    private String image;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<ToDoListEntity> toDoListEntities = new ArrayList<>();

//    @OneToMany(mappedBy = "userMap")
//    List<DiaryDTO> diaryDTOS = new ArrayList<>();
}
