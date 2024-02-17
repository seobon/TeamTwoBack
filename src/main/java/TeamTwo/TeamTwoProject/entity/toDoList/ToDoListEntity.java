package TeamTwo.TeamTwoProject.entity.toDoList;

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

    @Column(name = "userId", nullable = false)
    private int userId;

    @Column(name = "todoContent", nullable = false)
    private String todoContent;

    @Column(name = "createdAt", nullable = false)
    private String createdAt;

    @Column(name = "deadline")
    private String deadline;

    @Column(name = "state", nullable = false)
    private String state;
}
