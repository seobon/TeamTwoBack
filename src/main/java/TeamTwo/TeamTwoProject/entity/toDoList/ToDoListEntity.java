package TeamTwo.TeamTwoProject.entity.toDoList;

import TeamTwo.TeamTwoProject.entity.user.UserEntity;
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
@Table(name = "toDoList")
public class ToDoListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todoId", nullable = false)
    private int todoId;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
//    @Column(name = "id", nullable = false)
    private UserEntity user;

    @Column(name = "todoContent", nullable = false)
    private String todoContent;

    @Column(name = "createdAt", nullable = false)
    private String createdAt;

    @Column(name = "deadline")
    private String deadline;

    @Column(name = "state", nullable = false)
    private String state;
}
