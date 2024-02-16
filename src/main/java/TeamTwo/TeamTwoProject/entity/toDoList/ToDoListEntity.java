package TeamTwo.TeamTwoProject.entity.toDoList;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "toDoList")
public class ToDoListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todoId")
    private int todoId;

    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "todoContent", nullable = false)
    private String todoContent;

    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;

    @Column(name = "deadline")
    private String deadline;

    @Column(name = "state", nullable = false)
    private String state;
}
